package com.jejujg.controller;

import com.jejujg.payload.request.LoginRequest;
import com.jejujg.payload.request.SignupRequest;
import com.jejujg.payload.response.UserResponse;
import com.jejujg.security.CustomUserDetails;
import com.jejujg.security.JwtTokenProvider;
import com.jejujg.service.CookieService;
import com.jejujg.service.RedisService;
import com.jejujg.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CookieService cookieService;
    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/signup")
    public Long signup(@RequestBody SignupRequest request){
        return userService.save(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        CustomUserDetails authentication = (CustomUserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())).getPrincipal();
        String accessJwt = jwtTokenProvider.createAccessToken(authentication.getUsername());
        String refreshJwt = jwtTokenProvider.createRefreshToken(authentication.getUsername());

        Cookie accessToken = cookieService.createCookie("accessToken", accessJwt, JwtTokenProvider.accessTokenExpiration / 1000 * 30); // 30분
        Cookie refreshToken = cookieService.createCookie("refreshToken", refreshJwt, JwtTokenProvider.refreshTokenExpiration / 1000 / 7 * 30); // 30분

        redisService.setDataExpire(refreshJwt, authentication.getUsername(), JwtTokenProvider.refreshTokenExpiration / 1000);

        response.addCookie(accessToken);
        response.addCookie(refreshToken);

        return new ResponseEntity<>(accessJwt, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse responses){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cookie accessToken = cookieService.getCookie(request, "accessToken");
        Cookie refreshToken = cookieService.getCookie(request, "refreshToken");
        String username = null;

        try {
            username = jwtTokenProvider.getUsername(accessToken.getValue());
        } catch (ExpiredJwtException e){
            username = jwtTokenProvider.getUsername(e.getClaims().get("username").toString());
        } catch (NullPointerException e){
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }

        try {
            if (((CustomUserDetails)auth.getPrincipal()).getUsername().equals(username)){
                redisService.deleteData(refreshToken.getValue());

                accessToken.setMaxAge(0);
                accessToken.setPath("/");
                responses.addCookie(accessToken);

                refreshToken.setMaxAge(0);
                refreshToken.setPath("/");
                responses.addCookie(refreshToken);

                new SecurityContextLogoutHandler().logout(request, responses, auth);
            }
        } catch (IllegalArgumentException e){
            log.error("존재 하지 않는 유저입니다.");
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal CustomUserDetails userDetails){
        UserResponse user = UserResponse.builder()
                .username(userDetails.getUsername())
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .roles(userDetails.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()))
                .build();

        return user;
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
