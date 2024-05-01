package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@Data
public class Employee extends BaseEntity {


    private String firstName, lastName, email;

    @Column(columnDefinition = "Date")
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING) // hibernate will create table 0 and 1 is we don't use this annotation
    private Gender gender;
    private Double salary;

    @OneToOne(cascade = CascadeType.ALL)
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) // cascade allow us to join table and then save or remove from both tables
    @JoinColumn(name = "department_id") // this line of code represents column name in employees table using join
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;


    public Employee(String firstName, String lastName, String email, LocalDate hireDate, Gender gender, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.gender = gender;
        this.salary = salary;
    }
}
