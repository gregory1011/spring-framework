package com.cydeo;

import com.cydeo.Config.ConfigApp;
import com.cydeo.Service.SalaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class);

        SalaryService salaryService = container.getBean(SalaryService.class);
        salaryService.calculateRegularSalary();


    }


}
