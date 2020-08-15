package com.jejujg.service;

import com.jejujg.model.Role;
import com.jejujg.model.User;
import com.jejujg.model.UserRole;
import com.jejujg.payload.request.LoginRequest;
import com.jejujg.payload.request.SignupRequest;
import com.jejujg.payload.response.UserResponse;
import com.jejujg.repository.RoleRepository;
import com.jejujg.repository.UserRepository;
import com.jejujg.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
@Qualifier
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Long save(SignupRequest request){
        Role role = roleRepository.findByName(UserRole.ROLE_USER)
                .orElseThrow(() -> new IllegalArgumentException("권한이 설정 안됬습니다."));

        return userRepository.save(User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .name(request.getName())
            .password(passwordEncoder.encode(request.getPassword()))
            .roles(Collections.singleton(role))
            .build()).getId();
    }

    @Transactional
    public UserResponse findUser(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        return new UserResponse().toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("가입되지 않은 사용자 입니다."));

        return CustomUserDetails.create(user);
    }
}
