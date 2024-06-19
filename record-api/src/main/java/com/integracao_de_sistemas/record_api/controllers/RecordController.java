package com.integracao_de_sistemas.record_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.integracao_de_sistemas.record_api.DTOs.MessageDTO;
import com.integracao_de_sistemas.record_api.models.Message;
import com.integracao_de_sistemas.record_api.services.MessageService;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<?> saveMessage(@RequestBody MessageDTO messageDTO) {
        messageService.saveMessage(messageDTO);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages(@RequestParam("user") Long userId) {
        List<Message> messages = messageService.getMessagesByUserId(userId);
        return ResponseEntity.ok(messages);
    }
}

