package com.integracao_de_sistemas.auth_api.DTOs;

import java.io.Serializable;

public class RegisterDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String login;
    private String password;
    private String role;

    public RegisterDTO() {
    }

    public RegisterDTO(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
