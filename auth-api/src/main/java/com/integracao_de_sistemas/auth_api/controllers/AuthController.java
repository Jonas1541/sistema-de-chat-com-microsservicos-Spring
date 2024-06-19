package com.integracao_de_sistemas.auth_api.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.integracao_de_sistemas.auth_api.DTOs.LoginDTO;
import com.integracao_de_sistemas.auth_api.DTOs.UserDTO;
import com.integracao_de_sistemas.auth_api.config.JwtTokenProvider;
import com.integracao_de_sistemas.auth_api.models.User;
import com.integracao_de_sistemas.auth_api.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/token")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String token, @RequestParam("user") Long userId) {
        boolean isValid = jwtTokenProvider.validateToken(token, userId);
        return ResponseEntity.ok(Collections.singletonMap("auth", isValid));
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody LoginDTO loginDTO) {
        User user = userService.validateUser(loginDTO.getEmail(), loginDTO.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String token = jwtTokenProvider.createToken(user);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return ResponseEntity.ok(Collections.singletonMap("message", "ok"));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}

