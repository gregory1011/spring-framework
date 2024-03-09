package com.cydeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // annotate the class with controller stereotype annotation
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home.html";
    }

    @RequestMapping("/welcome")
    public String home2(){
        return "welcome.html";
    }

    @RequestMapping("/well")
    public String home3(){
        return "/user/userinfo.html";
    }

}
