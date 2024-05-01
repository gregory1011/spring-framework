package com.cydeo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "merchants")
@Data
@NoArgsConstructor
@Component
public class Merchant extends BaseModel{

    private String name, code;
    private BigDecimal transactionFee;
    private BigDecimal commissionRate;
    private Integer payoutDelayAccount;

    @OneToMany(mappedBy = "merchant") // in OneToMany relationship, ownership belongs to Many side.
    private List<Payment> paymentList; // we use list of payments because the @OneToMany requires one merchant to many payments, List<Payments>

    public Merchant(String name, String code, BigDecimal transactionFee, BigDecimal commissionRate, Integer payoutDelayAccount) {
        this.name = name;
        this.code = code;
        this.transactionFee = transactionFee;
        this.commissionRate = commissionRate;
        this.payoutDelayAccount = payoutDelayAccount;
    }
}
