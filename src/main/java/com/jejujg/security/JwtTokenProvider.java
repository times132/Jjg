package com.jejujg.security;

import com.jejujg.model.Role;
import com.jejujg.model.UserRole;
import com.jejujg.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService;

    @Value("${jwt.accesstoken.expiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refreshtoken.testExpiration}")
    private Long refreshTokenExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    // 토큰 생성
//    public String create(Authentication authentication, long expireTime){
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//
//        Claims claims = Jwts.claims().setSubject(userDetails.getUsername());
//        claims.put("roles", userDetails.getRoles());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * accesstokenExpiration))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
    public String create(String username, long expireTime){
        Claims claims = Jwts.claims();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String createAccessToken(String username){
        return create(username, accessTokenExpiration);
    }

    public String createRefreshToken(String username){
        return create(username, refreshTokenExpiration);
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return claims.get("username", String.class);
    }

    public boolean validateToken(String authToken){
        logger.info("엑세스 토큰: " + authToken);
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
