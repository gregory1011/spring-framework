package com.cydeo.controller;

import com.cydeo.enums.Color;
import com.cydeo.model.Fruit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fruits")
public class FruitsController {

    @RequestMapping("/all") // http://localhost:8080/fruits/all
    //http://localhost:8080/fruits/all?name=nuts&price=33
    public String getFruitList(@RequestParam String name, @RequestParam Integer price, Model model){

        model.addAttribute("name", name);
        model.addAttribute("price", price);
        System.out.println(name+" "+price);

        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(new Fruit("Banana", Color.Yellow, 0.50));
        fruitList.add(new Fruit("Kiwi", Color.Green, 0.88));
        fruitList.add(new Fruit("Peach", Color.Yellow, 1.50));
        fruitList.add(new Fruit("Orange", Color.Red, 0.99));
        model.addAttribute("fruit", fruitList);

        return "fruits/star-market";
    }

    @RequestMapping("/one")  // http://localhost:8080/fruits/one
    //http://localhost:8080/fruits/one?name=grapes&price=22
    public String getFruits2(@RequestParam(value = "name", required = false) String name, Integer price, Model model){


        model.addAttribute("name", name);
        model.addAttribute("price", price);
        System.out.println(name+" "+price);

        List<Fruit> fruitList = new ArrayList<>();
        fruitList.add(new Fruit("Banana", Color.Yellow, 0.50));
        fruitList.add(new Fruit("Kiwi", Color.Green, 0.88));
        model.addAttribute("fruit", fruitList);


        return "fruits/star-market";
    }

    @RequestMapping("/two/{name}/{price}") //  http://localhost:8080/fruits/two
    // http://localhost:8080/fruits/two/grapes/12
    public String getFruits3(@PathVariable String name, @PathVariable Integer price, Model model){

        model.addAttribute("name", name);
        model.addAttribute("price", price);
        System.out.println(name+" "+price);

        List<Fruit> fruitList = new ArrayList<>();

        fruitList.add(new Fruit("Banana", Color.Yellow, 0.50));
        fruitList.add(new Fruit("Kiwi", Color.Green, 0.88));
        model.addAttribute("fruit", fruitList);


        return "fruits/star-market";
    }

}
