package com.cydeo.stereotype_annotation;

import com.cydeo.stereotype_annotation.Config.ConfigApp;
import com.cydeo.stereotype_annotation.Model.DataStructure;
import com.cydeo.stereotype_annotation.Model.ExtraHours;
import com.cydeo.stereotype_annotation.Model.Microservice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class);

        DataStructure dataStructure = container.getBean(DataStructure.class);
        dataStructure.getTotalHours();

        Microservice microservice = container.getBean(Microservice.class);
        microservice.getTotalHours();

        Integer hours = container.getBean(ExtraHours.class).getHours();
        System.out.println(hours);


    }


}
