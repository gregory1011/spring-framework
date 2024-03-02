package com.cydeo.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigApp {

    @Bean
    Car car(){
        return new Car();
    }

    @Bean
    Dealer dealer(){
       return new Dealer();
    }

}
