package com.integracao_de_sistemas.auth_api.controllers;

import com.integracao_de_sistemas.auth_api.models.User;
import com.integracao_de_sistemas.auth_api.DTOs.AuthenticationDTO;
import com.integracao_de_sistemas.auth_api.DTOs.LoginResponseDTO;
import com.integracao_de_sistemas.auth_api.DTOs.RegisterDTO;
import com.integracao_de_sistemas.auth_api.config.TokenService;
import com.integracao_de_sistemas.auth_api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        if(this.userService.getUserByEmail(data.getLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getLogin(), encryptedPassword, data.getRole());

        this.userService.createUser(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/token")
    public ResponseEntity<Boolean> verifyToken(@RequestHeader("Authorization") String token, @RequestParam("user") Long userId) {
        boolean isValid = tokenService.validateToken(token, userId);
        return ResponseEntity.ok(isValid);
    }
}
