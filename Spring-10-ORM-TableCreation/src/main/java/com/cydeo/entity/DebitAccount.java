package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "DebitTable")
public class DebitAccount extends Account {

    private BigDecimal overdrawFee;
}
