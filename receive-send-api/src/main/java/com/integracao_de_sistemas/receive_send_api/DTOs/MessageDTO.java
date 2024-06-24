package com.integracao_de_sistemas.receive_send_api.DTOs;

import java.io.Serializable;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long userIdSend;
    private Long userIdReceive;
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(Long userIdSend, Long userIdReceive, String message) {
        this.userIdSend = userIdSend;
        this.userIdReceive = userIdReceive;
        this.message = message;
    }
    
    public Long getUserIdSend() {
        return userIdSend;
    }
    public void setUserIdSend(Long userIdSend) {
        this.userIdSend = userIdSend;
    }
    public Long getUserIdReceive() {
        return userIdReceive;
    }
    public void setUserIdReceive(Long userIdReceive) {
        this.userIdReceive = userIdReceive;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
