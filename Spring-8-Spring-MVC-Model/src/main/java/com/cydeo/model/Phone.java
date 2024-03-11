package com.cydeo.model;

import com.cydeo.enums.PhoneBrand;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Phone {

    private PhoneBrand brand;
    private String model;
    private Double price;
    private Boolean isNew;

}
