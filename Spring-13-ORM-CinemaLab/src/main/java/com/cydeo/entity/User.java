package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_account")
@Data
@NoArgsConstructor
public class User extends BaseModel{

    private String email, password, username;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_details_id")
    private Account account;
}
