package com.example.e_commerce_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
public class Inventory extends BaseEntity{

    private Integer quantityAvailable;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
}
