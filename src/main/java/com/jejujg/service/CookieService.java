package com.jejujg.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieService {

    @Value("${jwt.accesstoken.expiration}")
    private Integer tokenExpiration;

    public Cookie createCookie(String cookieName, String value){
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(tokenExpiration);
        cookie.setPath("/");

        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();

        if (cookies == null) return null;

        for (Cookie cookie : cookies){
            if (cookie.getName().equals(cookieName))
                return cookie;
        }

        return null;
    }
}
