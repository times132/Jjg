package com.jejujg.payload.request;

import com.jejujg.model.User;
import com.jejujg.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupRequest {

    private String email;
    private String password;
    private String username;
    private String name;

    @Builder
    public SignupRequest(String email, String password, String username, String name, UserRole role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .name(this.name)
                .build();
    }

    public void encodePassword(String password){
        this.password = password;
    }
}
