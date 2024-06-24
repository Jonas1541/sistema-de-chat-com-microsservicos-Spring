package com.integracao_de_sistemas.receive_send_api.DTOs;

import java.io.Serializable;

public class WorkerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userIdSend;
    private Long userIdReceive;

    public WorkerDTO() {
    }
    
    public WorkerDTO(Long userIdSend, Long userIdReceive) {
        this.userIdSend = userIdSend;
        this.userIdReceive = userIdReceive;
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
}
