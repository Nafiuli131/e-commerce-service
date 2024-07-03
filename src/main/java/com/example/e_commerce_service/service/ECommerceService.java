package com.example.e_commerce_service.service;
import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.entity.Item;

import java.time.LocalDate;
import java.util.List;
public interface ECommerceService {

    List<WishListItemDTO> getWishList(Long customerId);

    Double getTotalSaleAmountCurrentDay();

    LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate);

    List<TopSellingItemDTO> getTopSellingItemsAllTime();

    List<Item> getTopSellingItemsLastMonth();
}
