package com.albert.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private  static final String JWT_SECRET = "4oCcQWxiZXJ04oCd";
    private static final long JWT_EXPIRATION = 12 * 60 * 60 * 1000;
    public static String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .compact();
    }

    public static Claims parseToken(String token) throws Exception{
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
