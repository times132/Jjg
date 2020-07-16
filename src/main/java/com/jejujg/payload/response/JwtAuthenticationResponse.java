package com.jejujg.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JwtAuthenticationResponse {

    private String accessToken;

    @Builder
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
