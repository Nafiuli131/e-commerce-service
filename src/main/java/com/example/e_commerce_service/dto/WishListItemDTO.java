package com.example.e_commerce_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListItemDTO {
    private Long wishListId;
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private Double itemPrice;
    private Long customerId;
}
