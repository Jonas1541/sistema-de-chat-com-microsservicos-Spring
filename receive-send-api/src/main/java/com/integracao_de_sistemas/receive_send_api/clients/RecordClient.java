package com.integracao_de_sistemas.receive_send_api.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.integracao_de_sistemas.receive_send_api.DTOs.MessageDTO;
import com.integracao_de_sistemas.receive_send_api.models.Message;

@FeignClient(name = "record-api", url = "${record-api.url}")
public interface RecordClient {

    @PostMapping("/record/message")
    void saveMessage(@RequestBody MessageDTO messageDTO);

    @GetMapping("/record/messages")
    List<Message> getMessages(@RequestParam("user") Long userId);
}
