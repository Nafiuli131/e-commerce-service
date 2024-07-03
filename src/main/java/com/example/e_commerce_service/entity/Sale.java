package com.example.e_commerce_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale extends BaseEntity{

    private Integer quantity;
    private Double saleAmount;
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
