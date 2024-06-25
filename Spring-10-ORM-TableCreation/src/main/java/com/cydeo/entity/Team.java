package com.cydeo.entity;


import com.cydeo.enums.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column(name = "teamName")
    private String name;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Transient // with this ann. the column name 'founder' it won't appear in the table.
    private String founder;
}
