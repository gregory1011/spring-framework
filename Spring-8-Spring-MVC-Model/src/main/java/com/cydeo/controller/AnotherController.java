package com.cydeo.controller;

import com.cydeo.enums.Gender;
import com.cydeo.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/another")
public class AnotherController {

    @RequestMapping("/list")  // localhost:8080/another/mentor
    public String mentors(Model model){

        List<Mentor> mentorList = new ArrayList<>();
        mentorList.add(new Mentor("Mike", "Shale",45, Gender.Male));
        mentorList.add(new Mentor("Tom", "Hanks",65, Gender.Male));
        mentorList.add(new Mentor("Ana", "Armors",25, Gender.Female));

        model.addAttribute("mentors", mentorList);

        return "mentor-list";  // packagename/filename
    }

}
