package com.integracao_de_sistemas.receive_send_api.models;

public class Message {
    
    private Long userIdSend;
    private Long userIdReceive;
    private String message;

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
