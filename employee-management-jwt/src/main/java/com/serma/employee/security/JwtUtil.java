
package com.serma.employee.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key = Keys.hmacShaKeyFor(System.getenv().getOrDefault("JWT_SECRET","replace_with_a_very_long_secret_for_dev_purpose_only").getBytes());
    private final long validity = 1000L * 60 * 60 * 24; // 24h

    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> validateToken(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        } catch (JwtException e) {
            throw new RuntimeException("Invalid JWT token");
        }
    }
}
