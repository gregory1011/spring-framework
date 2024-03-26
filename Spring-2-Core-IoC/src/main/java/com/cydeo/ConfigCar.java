package com.cydeo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCar {

    @Bean
    Car car(){
        Car c = new Car();
        c.setMake("BMW");
        return c;
    }

//    //Direct wiring
//    @Bean
//    Person person(){
//        Person p = new Person();
//        p.setName("Messi");
//        p.setCar(car());
//        return p;
//    }


    // Auto-wiring
    @Bean
    Person person(Car car){
        Person p = new Person();
        p.setName("Messi");
        p.setCar(car());
        return p;
    }

}
