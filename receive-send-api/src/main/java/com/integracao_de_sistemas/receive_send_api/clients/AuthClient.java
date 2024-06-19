package com.integracao_de_sistemas.receive_send_api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-api", url = "http://localhost:8081")
public interface AuthClient {

    @GetMapping("/auth/token")
    boolean verifyToken(@RequestHeader("Authorization") String token, @RequestParam("user") Long userId);

    @GetMapping("/auth/user")
    User getUser(@RequestParam("email") String email);
}
