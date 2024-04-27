package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CarTable")
@NoArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name, model;

    public Car(String name, String model) {
        this.name = name;
        this.model = model;
    }
}
