package com.cydeo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "CreditTable")
public class CreditAccount extends Account{

    BigDecimal creditLimit;
}
