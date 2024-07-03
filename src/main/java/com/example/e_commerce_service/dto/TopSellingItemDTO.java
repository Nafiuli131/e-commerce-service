package com.example.e_commerce_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopSellingItemDTO {
    private Long itemId;
    private Double totalSaleAmount;

}
