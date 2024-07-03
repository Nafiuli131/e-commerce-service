package com.example.e_commerce_service.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends BaseEntity{

    private String name;
    private String description;
    private Double price;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private Inventory inventory;
}
