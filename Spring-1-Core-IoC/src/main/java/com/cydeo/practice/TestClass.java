package com.cydeo.practice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestClass {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class);

        container.getBean(Dealer.class).getContract();
        container.getBean( Car.class).getCarito();

    }


}
