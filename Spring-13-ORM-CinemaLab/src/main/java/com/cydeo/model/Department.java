package com.cydeo.model;

import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
public class Department{

    @Id
    private String department;
    private String division;

}
