package com.jejujg.payload.response;

import com.jejujg.model.User;
import com.jejujg.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponse {

    private Long id;
    private String email;
    private String username;
    private String name;
    private UserRole role;

    @Builder
    public UserResponse(Long id, String email, String username, String name, UserRole role){
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public UserResponse toDto(User user){
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}
