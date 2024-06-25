package com.integracao_de_sistemas.receive_send_api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.integracao_de_sistemas.receive_send_api.DTOs.AuthenticationDTO;
import com.integracao_de_sistemas.receive_send_api.DTOs.LoginResponseDTO;
import com.integracao_de_sistemas.receive_send_api.DTOs.RegisterDTO;
import com.integracao_de_sistemas.receive_send_api.models.User;

import jakarta.validation.Valid;

@FeignClient(name = "auth-api", url = "${auth-api.url}")
public interface AuthClient {

    @GetMapping("/auth/token")
    boolean verifyToken(@RequestHeader("Authorization") String token, @RequestParam("user") Long userId);

    @GetMapping("/auth/user")
    User getUser(@RequestParam("email") String email);

    @PostMapping("/auth/login")
    LoginResponseDTO login(@RequestBody AuthenticationDTO loginRequest);

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data);
}

