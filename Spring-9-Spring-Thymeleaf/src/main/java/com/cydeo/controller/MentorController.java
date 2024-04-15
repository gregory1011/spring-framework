package com.cydeo.controller;

import com.cydeo.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @GetMapping("/register")
    public String showForm(Model model){

        model.addAttribute("mentor", new Mentor() );

        List<String> batchList = Arrays.asList("JD1", "JD2", "JD3", "JD4", "JD5");
        model.addAttribute("batchList", batchList);

        return "/mentor/mentor-register";
    }

    @PostMapping("/confirm")
    public String showForm2(@ModelAttribute("mentor") Mentor mentor, Model model){

       // return "/mentor/mentor-confirmation";
        return "redirect:/mentor/register"; // this means the form data will be submited then redirect back to mentor/register
    }
}
