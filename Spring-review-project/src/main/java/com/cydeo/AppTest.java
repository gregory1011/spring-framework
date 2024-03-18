package com.cydeo;

import com.cydeo.Config.ConfigApp;
import com.cydeo.Service.OvertimeSalaryService;
import com.cydeo.Service.SalaryService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class);

        SalaryService salaryService1 = container.getBean(SalaryService.class);
        salaryService1.calculateRegularSalary();

        SalaryService salaryService2 = container.getBean(SalaryService.class);
        salaryService2.calculateRegularSalary();

        System.out.println(salaryService1 == salaryService2);

        OvertimeSalaryService overtimeSalaryService = container.getBean(OvertimeSalaryService.class);
        overtimeSalaryService.calculateOvertimeSalary();


    }


}
