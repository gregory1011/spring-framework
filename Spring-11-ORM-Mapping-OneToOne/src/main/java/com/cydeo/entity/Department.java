package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class Department extends BaseEntity{

    private String department, division;
    // if we want to join 2 table one to one
    @OneToOne(mappedBy = "department") // it must be same name as in Employee table - private Department department;
    // this line of code representing we want to join on the data from the
    private Employee employee; //

    public Department(String department, String division) {
        this.department = department;
        this.division = division;
    }
}
