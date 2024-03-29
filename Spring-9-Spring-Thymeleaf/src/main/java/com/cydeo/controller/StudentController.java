package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student") // port number 9090
public class StudentController {

    @RequestMapping("/register")
    public String register(Model model){

        model.addAttribute("students", DataGenerator.createStudent());


        return "student/register";
    }

    //@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    @GetMapping("/welcome")
    public String info(@RequestParam Integer id){

        System.out.println("id ="+id);

        return "student/welcome";
    }

   // @RequestMapping(value = "/welcome2", method = RequestMethod.POST)
    @PostMapping("/welcome2")
    public String info2( @RequestParam Integer age){

        System.out.println("age ="+age);

        return "student/welcome";
    }

}
