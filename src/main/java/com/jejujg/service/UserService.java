package com.jejujg.service;

import com.jejujg.model.User;
import com.jejujg.model.UserRole;
import com.jejujg.payload.request.SignupRequest;
import com.jejujg.payload.response.UserResponse;
import com.jejujg.repository.UserRepository;
import com.jejujg.security.CustomUserDetails;
import lombok.AllArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long save(SignupRequest dto){
        User user = dto.toEntity();
        dto.encodePassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(dto.toEntity()).getId();
    }

    public UserResponse findUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        return new UserResponse().toDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return CustomUserDetails.create(user);
    }
}
