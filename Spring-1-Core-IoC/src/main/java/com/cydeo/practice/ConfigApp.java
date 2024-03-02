package com.cydeo.practice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan
public class ConfigApp {


    Car car(){
        return new Car();
    }


    Dealer dealer(){
       return new Dealer();
    }

}
