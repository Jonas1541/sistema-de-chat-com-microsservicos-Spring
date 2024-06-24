package com.integracao_de_sistemas.record_api.listeners;

import com.integracao_de_sistemas.record_api.DTOs.MessageDTO;
import com.integracao_de_sistemas.record_api.models.Message;
import com.integracao_de_sistemas.record_api.repositories.MessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private MessageRepository messageRepository;

    @RabbitListener(queues = "messageQueue")
    public void handleMessage(MessageDTO messageDTO) {
        // Armazena a mensagem na tabela de histórico
        Message message = new Message();
        message.setUserIdSend(messageDTO.getUserIdSend());
        message.setUserIdReceive(messageDTO.getUserIdReceive());
        message.setMessage(messageDTO.getMessage());
        messageRepository.save(message);
    }
}
