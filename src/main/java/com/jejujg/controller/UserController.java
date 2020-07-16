package com.jejujg.controller;

import com.jejujg.payload.request.LoginRequest;
import com.jejujg.payload.request.SignupRequest;
import com.jejujg.payload.response.JwtAuthenticationResponse;
import com.jejujg.payload.response.UserResponse;
import com.jejujg.security.CustomUserDetails;
import com.jejujg.security.JwtTokenProvider;
import com.jejujg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public Long signup(@RequestBody SignupRequest request){
        return userService.save(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@RequestBody LoginRequest request){
        UserResponse response = userService.login(request);
        String token = jwtTokenProvider.create(response.getUsername(), response.getRole());
        JwtAuthenticationResponse accesstoken = JwtAuthenticationResponse.builder()
                .accessToken(token)
                .build();

        return accesstoken;
    }

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal CustomUserDetails userDetails){
        UserResponse user = UserResponse.builder()
                .username(userDetails.getUsername())
                .name(userDetails.getName())
                .email(userDetails.getEmail())
                .build();
        return user;
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
