package com.cydeo.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cinema extends BaseModel{

    private String name, sponsorName;

    public Cinema(String name) {
        this.name = name;
    }

    private Location location;
}
