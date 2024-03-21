package com.cydeo.controller;

import com.cydeo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

   @RequestMapping("/welcome")
    public String homePage(Model model){

       model.addAttribute("name", "Cydeo");
       model.addAttribute("course", "MVC");

       String subject = "Collections";
       model.addAttribute("subject", subject);

       // create some random student ID and show it in UI
       Integer id = 13;
       model.addAttribute("id", id);

       List<Integer> numbers = new ArrayList<>();
       numbers.add(4);
       numbers.add(5);
       numbers.add(7);
       numbers.add(9);
       model.addAttribute("numbers", numbers);

       // creating one object from student class and assigning value
      Student student = new Student(13, "Leo", "Messi");
      //                                ( key ,  value );
      model.addAttribute("student", student);

        return "student/welcome"; // packagename/filename
    }

}
