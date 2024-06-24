package com.integracao_de_sistemas.auth_api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.integracao_de_sistemas.auth_api.models.User;
import com.integracao_de_sistemas.auth_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private final UserRepository userRepository;

    public TokenService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public boolean validateToken(String token, Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String email = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
            User user = userRepository.findByEmail(email);
            return user != null && user.getId().equals(userId);
        } catch (JWTVerificationException exception){
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token", exception);
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
