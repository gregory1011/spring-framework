package com.cydeo.bean_practice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {

    public static void main(String[] args) {

        ApplicationContext container = new AnnotationConfigApplicationContext(ConfigApp.class, ConfigApp2.class);

        FullTimeEmployee employeeF = container.getBean(FullTimeEmployee.class);
        PartTimeEmployee employeeP = container.getBean(PartTimeEmployee.class);
        employeeF.createAccount();
        employeeP.createAccount();

        String str1 = container.getBean("method1",String.class);
        System.out.println(str1);
        System.out.println( container.getBean(ConfigApp2.class).method2() );




    }
}
