package com.cydeo.model;

import com.cydeo.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Fruit {

    private String name;
    private Color color;
    private Double price;

}
