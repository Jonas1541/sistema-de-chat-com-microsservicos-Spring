package com.integracao_de_sistemas.receive_send_api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.RequestInterceptor;

@Configuration
public class FeignClientConfig {

    @Value("${auth-api.url}")
    private String authApiUrl;

    @Value("${record-api.url}")
    private String recordApiUrl;

    @Bean
    public RequestInterceptor authApiRequestInterceptor() {
        return requestTemplate -> requestTemplate.target(authApiUrl);
    }

    @Bean
    public RequestInterceptor recordApiRequestInterceptor() {
        return requestTemplate -> requestTemplate.target(recordApiUrl);
    }
}
