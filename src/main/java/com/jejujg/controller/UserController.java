package com.jejujg.controller;

import com.jejujg.payload.request.LoginRequest;
import com.jejujg.payload.request.SignupRequest;
import com.jejujg.payload.response.JwtAuthenticationResponse;
import com.jejujg.payload.response.UserResponse;
import com.jejujg.security.CustomUserDetails;
import com.jejujg.security.JwtTokenProvider;
import com.jejujg.service.CookieService;
import com.jejujg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CookieService cookieService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public Long signup(@RequestBody SignupRequest request){
        return userService.save(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response){
//        UserResponse response = userService.login(request);
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtTokenProvider.create(authentication);
//        JwtAuthenticationResponse accesstoken = JwtAuthenticationResponse.builder()
//                .accessToken(token)
//                .build();
//
//        return accesstoken;
        CustomUserDetails authentication = (CustomUserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String accessJwt = jwtTokenProvider.createAccessToken(authentication.getUsername());
        String refreshJwt = jwtTokenProvider.createRefreshToken(authentication.getUsername());

        Cookie accessToken = cookieService.createCookie()
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
