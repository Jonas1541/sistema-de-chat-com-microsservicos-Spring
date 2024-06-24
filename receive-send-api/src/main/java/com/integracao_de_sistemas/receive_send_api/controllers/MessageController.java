package com.integracao_de_sistemas.receive_send_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.integracao_de_sistemas.receive_send_api.DTOs.MessageDTO;
import com.integracao_de_sistemas.receive_send_api.DTOs.WorkerDTO;
import com.integracao_de_sistemas.receive_send_api.clients.AuthClient;
import com.integracao_de_sistemas.receive_send_api.clients.RecordClient;
import com.integracao_de_sistemas.receive_send_api.models.Message;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private AuthClient authClient;

    @Autowired
    private RecordClient recordClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestHeader("Authorization") String token, @RequestBody MessageDTO messageDTO) {
        boolean isAuthenticated = authClient.verifyToken(token, messageDTO.getUserIdSend());
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not auth");
        }
        // Envia mensagem para a fila
        rabbitTemplate.convertAndSend("messageQueue", messageDTO);
        // Salva a mensagem na tabela de histórico
        recordClient.saveMessage(messageDTO);
        return ResponseEntity.ok("message sent with success");
    }

    @PostMapping("/worker")
    public ResponseEntity<?> processMessages(@RequestHeader("Authorization") String token, @RequestBody WorkerDTO workerDTO) {
        boolean isAuthenticated = authClient.verifyToken(token, workerDTO.getUserIdSend());
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not auth");
        }
        // Processa mensagens da fila (não implementado aqui, mas seria necessário um listener do RabbitMQ)
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public ResponseEntity<?> getMessages(@RequestHeader("Authorization") String token, @RequestParam("user") Long userId) {
        boolean isAuthenticated = authClient.verifyToken(token, userId);
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not auth");
        }
        List<Message> messages = recordClient.getMessages(userId);
        return ResponseEntity.ok(messages);
    }
}
