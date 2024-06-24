package com.integracao_de_sistemas.auth_api.DTOs;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
