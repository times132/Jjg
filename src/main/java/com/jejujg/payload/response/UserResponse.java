package com.jejujg.payload.response;

import com.jejujg.model.Role;
import com.jejujg.model.User;
import com.jejujg.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserResponse {

//    private Long id;
    private String email;
    private String username;
    private String name;
    private Set<UserRole> roles;

    @Builder
    public UserResponse(Long id, String email, String username, String name, Set<UserRole> roles){
//        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.roles = roles;
    }

    public UserResponse toDto(User user){
        return UserResponse.builder()
//                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .name(user.getName())
                .roles(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()))
                .build();
    }
}
