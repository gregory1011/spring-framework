package com.cydeo.controller;

import com.cydeo.enums.Gender;
import com.cydeo.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

  @RequestMapping("/list") // localhost:8080/mentor/list
  public String mentors(Model model){

      List<Mentor> mentorList = new ArrayList<>();
      mentorList.add(new Mentor("Mike", "Shale",45, Gender.Male));
      mentorList.add(new Mentor("Tom", "Hanks",65, Gender.Male));
      mentorList.add(new Mentor("Ana", "Armors",25, Gender.Female));

      model.addAttribute("mentors", mentorList);

      return "mentor/mentor-list"; // packagename/filename
    }

}
