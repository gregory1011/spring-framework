package com.cydeo.bean_annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FullTimeMentor {

    public void createAccount(){
        System.out.println("Full time mentor acc is created...");
    }


}
