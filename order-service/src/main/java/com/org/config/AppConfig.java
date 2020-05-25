package com.org.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {


    @Bean
    // @LoadBalanced (Not implemented now)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
