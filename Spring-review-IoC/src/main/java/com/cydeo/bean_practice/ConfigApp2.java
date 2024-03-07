package com.cydeo.bean_practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigApp2 {

    @Bean
    public String method1(){
        return "Welcome to Cydeo";
    }

    @Bean
    public String method2(){
        return "Spring Core Practice";
    }
}
