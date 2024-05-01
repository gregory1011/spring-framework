package com.cydeo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart extends BaseModel{


    // in this case the ManyToMany will create a new table for relationship.
    @ManyToMany
    @JoinTable(name = "cart_items_rel",  // rename new table name
            joinColumns = @JoinColumn(name = "c_id"), // rename cart column
            inverseJoinColumns = @JoinColumn(name = "i_id")) // rename item column
    private List<Item> itemList;
}
