package com.Karl.SecureDesk.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private String secretKeyString = "122d7e9db54b1fa1315fabfc8a5ab4c2e682d9d512980d83a2976f599e1ed7c3";
    private SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes());

    public String generateToken(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public String extractUserName(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(){
        return true;
    }
}
