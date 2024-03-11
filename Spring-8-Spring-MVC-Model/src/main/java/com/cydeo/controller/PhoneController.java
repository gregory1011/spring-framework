package com.cydeo.controller;

import com.cydeo.enums.PhoneBrand;
import com.cydeo.model.Phone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/store") // http://localhost:8080/store
public class PhoneController {

    @RequestMapping("/phone")   // http://localhost:8080/store/phone
    public String getPhoneList (Model model){

        Phone apple = new Phone(PhoneBrand.Apple, "Iphone XR", 500D, false);
        Phone samsung = new Phone(PhoneBrand.Samsung, "Samsung X11", 230D, false);
        Phone nokia = new Phone(PhoneBrand.Nokia, "Nokia K640", 210D, false);
        Phone apple1 = new Phone(PhoneBrand.Apple, "Iphone 12 Max Pro", 650D, true);

        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(0, apple);
        phoneList.add(1, apple1);
        phoneList.add(2, nokia);
        phoneList.add(3, samsung);

        model.addAttribute("phone", phoneList);


        return "phone/walmart"; // packagename/filename
    }



}
