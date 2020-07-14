package com.jejujg.payload.request;

import com.jejujg.model.User;
import com.jejujg.model.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequest {

    private String email;
    private String password;
    private String username;
    private String name;
    private UserRole role;

    @Builder
    public SignupRequest(String email, String password, String username, String name, UserRole role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .name(this.name)
                .role(this.role)
                .build();
    }

    public void encodePassword(String password){
        this.password = password;
    }
}
