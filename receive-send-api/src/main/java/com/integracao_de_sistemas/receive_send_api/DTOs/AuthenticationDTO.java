package com.integracao_de_sistemas.receive_send_api.DTOs;

import java.io.Serializable;

public class AuthenticationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String password;

    public AuthenticationDTO() {
    }

    public AuthenticationDTO(String login, String password) {
        this.login = login;
        this.password = password;
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
}
