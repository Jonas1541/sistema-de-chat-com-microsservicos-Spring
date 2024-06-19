package com.integracao_de_sistemas.receive_send_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReceiveSendApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiveSendApiApplication.class, args);
	}

}
