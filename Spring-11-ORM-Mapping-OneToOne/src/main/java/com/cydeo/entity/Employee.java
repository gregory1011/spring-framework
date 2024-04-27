package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee extends BaseEntity {


    private String firstName, lastName, email;

    @Column(columnDefinition = "Date")
    private LocalDate hireDate;

//    private String department;
    @Enumerated(EnumType.STRING) // hibernate will create table 0 and 1 is we don't use this annotation
    private Gender gender;
    private Double salary;
//    private Integer regionId;


}
