package com.integracao_de_sistemas.receive_send_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {
    
    @Bean
    public Queue messageQueue() {
        return new Queue("messageQueue", true);
    }
}
