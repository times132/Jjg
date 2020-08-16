package com.jejujg.security;

import com.jejujg.service.CookieService;
import com.jejujg.service.RedisService;
import com.jejujg.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CookieService cookieService;
    private final UserService userService;
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie jwtToken = cookieService.getCookie(request, "accessToken");

        String username = null;
        String jwt = null;
        String refreshJwt = null;
        String refreshUsername = null;

        try {
            if (jwtToken != null){ // accessToken이 있으면 토큰에서 사용자 이름을 불러옴
                jwt = jwtToken.getValue();
                username = jwtTokenProvider.getUsername(jwt);
            }
            if (username != null){ // accessToken이 유효하면 사용자 정보로 userdetails 생성
                UserDetails userDetails = userService.loadUserByUsername(username);

                if (jwtTokenProvider.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException | NullPointerException e) { // accessToken의 기간이 만료된 경우 쿠키에서 refreshToken을 읽어옴
            Cookie refreshToken = cookieService.getCookie(request, "refreshToken");
            if (refreshToken != null){
                refreshJwt = refreshToken.getValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (refreshJwt != null) { // refreshToken에서 유저 정보를 읽어 accessToken을 새로 생성해서 전달
                refreshUsername = redisService.getData(refreshJwt);

                // refreshToken 만료 기간이 일정 이하로 남았을 때 redis에서 삭제하고 재발급
                if (jwtTokenProvider.getTokenExpired(refreshJwt).getTime() - System.currentTimeMillis() < 300000){
                    String newRefreshToken = jwtTokenProvider.createRefreshToken(refreshUsername);

                    Cookie newRefreshCookie = cookieService.createCookie("refreshToken", newRefreshToken, JwtTokenProvider.refreshTokenExpiration / 1000 / 7 * 10);
                    response.addCookie(newRefreshCookie);

                    redisService.deleteData(refreshJwt);
                    redisService.setDataExpire(newRefreshToken, refreshUsername, JwtTokenProvider.refreshTokenExpiration / 1000);
                }

                // redis에 저장된 유저와 토큰의 저장된 유저가 같은 경우 accessToken을 재발급, 세션 유지
                if (refreshUsername.equals(jwtTokenProvider.getUsername(refreshJwt))) {
                    UserDetails userDetails = userService.loadUserByUsername(refreshUsername);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    String newAccessToken = jwtTokenProvider.createAccessToken(refreshUsername);

                    Cookie newAccessCookie = cookieService.createCookie("accessToken", newAccessToken, JwtTokenProvider.accessTokenExpiration / 1000 * 3);
                    response.addCookie(newAccessCookie);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }


}
