package com.cydeo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CydeoApp {

    public static void main(String[] args) {


        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class);

        Python python = container.getBean(Python.class);
        python.getTeachingHours();
        container.getBean(Selenium.class).getTeachingHours();
        System.out.println("Office hours = "+container.getBean(OfficeHours.class).getHours());

    }


}
