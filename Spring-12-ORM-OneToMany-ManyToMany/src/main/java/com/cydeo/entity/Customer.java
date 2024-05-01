package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer extends BaseModel{

    private String userName, name, surName, email, address;

    // giving the ownership to Payment table, one costumer to many payments
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)     // -
    private List<Payment> payment;

    public Customer(String userName, String name, String surName, String email, String address) {
        this.userName = userName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.address = address;
    }

}
