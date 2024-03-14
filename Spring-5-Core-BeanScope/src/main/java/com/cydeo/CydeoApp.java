package com.cydeo;

import com.cydeo.config.ProjectConfig;
import com.cydeo.model.Comment;
import com.cydeo.service.CommentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CydeoApp {

    public static void main(String[] args) {

        Comment comment = new Comment();
        comment.setAuthor("Shakespeare");
        comment.setText("Romeo and Juliet");

        ApplicationContext container = new AnnotationConfigApplicationContext(ProjectConfig.class);

        CommentService  cs1 = container.getBean(CommentService.class);
        CommentService  cs2 = container.getBean(CommentService.class);

        System.out.println(cs1);
        System.out.println(cs2);

        System.out.println(cs1 == cs2);  //is true. -But after using @Scope(prototype) it becomes false




    }


}
