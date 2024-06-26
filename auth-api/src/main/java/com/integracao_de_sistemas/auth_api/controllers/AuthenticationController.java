package com.integracao_de_sistemas.auth_api.controllers;

import com.integracao_de_sistemas.auth_api.models.User;
import com.integracao_de_sistemas.auth_api.DTOs.AuthenticationDTO;
import com.integracao_de_sistemas.auth_api.DTOs.LoginResponseDTO;
import com.integracao_de_sistemas.auth_api.DTOs.RegisterDTO;
import com.integracao_de_sistemas.auth_api.config.TokenService;
import com.integracao_de_sistemas.auth_api.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder; // Adicione isso

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        logger.info("Attempting login for user: " + data.getLogin());
        try {
            User user = userService.getUserByEmail(data.getLogin());
            if (user != null) {
                logger.info("User found: " + user.getEmail());
                if (passwordEncoder.matches(data.getPassword(), user.getPassword())) {
                    var token = tokenService.generateToken(user);
                    logger.info("Login successful for user: " + data.getLogin());
                    return ResponseEntity.ok(new LoginResponseDTO(token));
                } else {
                    logger.error("Password mismatch for user: " + data.getLogin());
                }
            } else {
                logger.error("User not found: " + data.getLogin());
            }
            return ResponseEntity.status(403).body("Authentication failed");
        } catch (Exception e) {
            logger.error("Login failed for user: " + data.getLogin(), e);
            return ResponseEntity.status(403).body("Authentication failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if(this.userService.getUserByEmail(data.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = passwordEncoder.encode(data.getPassword());
        User newUser = new User(data.getLogin(), encryptedPassword, data.getRole());
        this.userService.createUser(newUser);

        logger.info("User created: " + newUser.getEmail() + " with encrypted password: " + newUser.getPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token")
    public ResponseEntity<Boolean> verifyToken(@RequestHeader("Authorization") String token,
            @RequestParam("user") Long userId) {
        boolean isValid = tokenService.validateToken(token, userId);
        return ResponseEntity.ok(isValid);
    }
}
