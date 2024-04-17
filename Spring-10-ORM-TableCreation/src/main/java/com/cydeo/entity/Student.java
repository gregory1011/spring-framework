package com.cydeo.entity;

import com.cydeo.enums.Gender;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentFirstName")
    private String firstName;
    @Column(name = "studentLastName")

    private String lastName;

    private String email;

    @Transient // if we want this city column to not be shown in the table
    private String city;

    @Column(columnDefinition = "DATE")
    private LocalDate birthday;
    @Column(columnDefinition = "TIME")

    private LocalTime birthtime;
    @Column(columnDefinition = "TIMESTAMP")

    private LocalDate birthdateTime;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
