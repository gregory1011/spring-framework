package com.cydeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients // for consuming 3rd party api with FeignClients
public class Spring17RestConsumingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring17RestConsumingApiApplication.class, args);
    }

    // 3rd party injection to consume RestTemplate API.
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
