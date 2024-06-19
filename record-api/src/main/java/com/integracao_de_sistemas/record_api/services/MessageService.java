package com.integracao_de_sistemas.record_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.integracao_de_sistemas.record_api.DTOs.MessageDTO;
import com.integracao_de_sistemas.record_api.models.Message;
import com.integracao_de_sistemas.record_api.repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setUserIdSend(messageDTO.getUserIdSend());
        message.setUserIdReceive(messageDTO.getUserIdReceive());
        message.setMessage(messageDTO.getMessage());
        messageRepository.save(message);
    }

    public List<Message> getMessagesByUserId(Long userId) {
        return messageRepository.findByUserIdReceive(userId);
    }
}

