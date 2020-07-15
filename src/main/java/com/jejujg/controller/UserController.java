package com.jejujg.controller;

import com.jejujg.payload.request.LoginRequest;
import com.jejujg.payload.request.SignupRequest;
import com.jejujg.payload.response.UserResponse;
import com.jejujg.security.JwtTokenProvider;
import com.jejujg.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/signup")
    public Long signup(@RequestBody SignupRequest request){
        return userService.save(request);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody LoginRequest request){
        UserResponse response = userService.login(request);

        return jwtTokenProvider.create(response.getUsername(), response.getRole());
    }
}
