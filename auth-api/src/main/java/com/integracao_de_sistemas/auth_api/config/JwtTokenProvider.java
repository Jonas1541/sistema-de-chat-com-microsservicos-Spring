package com.integracao_de_sistemas.auth_api.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.integracao_de_sistemas.auth_api.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    private String secretKey = "secret";

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1h

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token, Long userId) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return claims.get("userId").equals(userId);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

