
package com.cydeo.model;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Employee {

    private String name;
    private String department;
    private Double hourlyRate;


}
