package com.asalkar.dto.utility;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    // The secret key should be at least 256 bits (32 bytes) for HS256
   // @Value("${jwt.secret}")
    private static String secretKey="XU9vL!k7dQaP%&Nz8rE$McB*JzW@H#2x";

    @Value("${jwt.expiration.ms}") // Default to 1 day if not set
    private  long expirationTimeMs;

    private  Key getSigningKey() {
        // Ensure the secret key is properly formatted and meets the required length
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public  String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs)) // Dynamic expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Use dynamic key
                .compact();
    }
}
