package com.example.e_commerce_service.service;

import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.entity.Item;
import com.example.e_commerce_service.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ECommerceServiceImpl implements ECommerceService {

    private final ItemRepository itemRepository;

    @Autowired
    public ECommerceServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<WishListItemDTO> getWishList(Long customerId) {
        List<Object[]> results = itemRepository.findWishListByCustomerId(customerId);
        return results.stream()
                .map(result -> {
                    WishListItemDTO itemDTO = new WishListItemDTO();
                    itemDTO.setItemId((Long) result[0]);
                    itemDTO.setItemDescription((String) result[1]);
                    itemDTO.setCustomerId((Long)result[2]);
                    itemDTO.setItemPrice((Double) result[3]);
                    itemDTO.setWishListId((Long) result[4]);
                    itemDTO.setItemName((String) result[5]);
                    return itemDTO;
                })
                .collect(Collectors.toList());
    }


    @Override
    public Double getTotalSaleAmountCurrentDay() {
        return itemRepository.calculateTotalSaleAmountCurrentDay();
    }

    @Override
    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        return itemRepository.findMaxSaleDay(startDate, endDate);
    }

    @Override
    public List<TopSellingItemDTO> getTopSellingItemsAllTime() {
        return itemRepository.findTopSellingItems().stream().limit(3).collect(Collectors.toList());
    }

    @Override
    public List<Item> getTopSellingItemsLastMonth() {
        return null;
//        return itemRepository.findTopSellingItemsLastMonth();
    }
}
