package com.integracao_de_sistemas.receive_send_api.models;

public class Message {
    
    private Long userId;
    private String message;

    public Message() {
    }
    
    public Message(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
