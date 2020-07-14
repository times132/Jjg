package com.jejujg.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${jwt.expirationMin}")
    private Long jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    // 토큰 생성
    public String create(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(Long.toString(userDetails.getId()))
//                .claim("email", userDetails.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e){
            logger.error("서명을 확인 할 수 없는 토큰입니다.");
        }catch (MalformedJwtException e){
            logger.error("올바른 토큰이 아닙니다.");
        }catch (ExpiredJwtException e){
            logger.error("유효기간이 만료된 토큰입니다.");
        }catch (UnsupportedJwtException e){
            logger.error("지원하지 않는 토큰입니다.");
        }catch (IllegalArgumentException e){
            logger.error("토큰에 값이 없습니다.");
        }
        return false;
    }
}
