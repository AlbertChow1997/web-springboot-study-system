package com.albert;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt(){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", 1);
        dataMap.put("username", "admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "4oCcQWxiZXJ04oCd")//add signature algorithm and key
                .addClaims(dataMap)//add Claims
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))//add expiration time at
                .compact();//generate jwt

        System.out.println("jwt: " + jwt);
    }

    @Test
    public void testParseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc3MTExODA5M30.QJXbCQhotfT_G3IIcCUVhBGYg0RGB5G4SkA6DQjhpzw";
        Claims claims = Jwts.parser()
                .setSigningKey("4oCcQWxiZXJ04oCd")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("claims: " + claims);
    }
}
