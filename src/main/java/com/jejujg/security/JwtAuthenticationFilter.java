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
        } catch (ExpiredJwtException e) { // accessToken의 기간이 만료된 경우 쿠키에서 refreshToken을 읽어옴
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

                if (refreshUsername.equals(jwtTokenProvider.getUsername(refreshJwt))) {
                    UserDetails userDetails = userService.loadUserByUsername(refreshUsername);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                    String newToken = jwtTokenProvider.createAccessToken(refreshUsername);

                    Cookie newAccessToken = cookieService.createCookie("accessToken", newToken, JwtTokenProvider.accessTokenExpiration / 100);
                    response.addCookie(newAccessToken);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
//        try {
//            String token = getJwtFromRequest(request);
//
//            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
//                Authentication authentication = jwtTokenProvider.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }catch (Exception e){
//            logger.error("Could not set user authentication in security context", e);
//        }
//
//        filterChain.doFilter(request, response);
    }


}
