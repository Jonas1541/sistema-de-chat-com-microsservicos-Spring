package com.integracao_de_sistemas.record_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userIdSend;
    private Long userIdReceive;
    private String message;

    public Message() {
    }

    public Message(Long id, Long userIdSend, Long userIdReceive, String message) {
        this.id = id;
        this.userIdSend = userIdSend;
        this.userIdReceive = userIdReceive;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
