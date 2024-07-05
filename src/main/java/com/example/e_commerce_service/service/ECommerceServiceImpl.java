package com.example.e_commerce_service.service;

import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.repository.ItemRepository;
import com.example.e_commerce_service.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ECommerceServiceImpl implements ECommerceService {

    private final ItemRepository itemRepository;

    private final SaleRepository saleRepository;

    @Autowired
    public ECommerceServiceImpl(ItemRepository itemRepository, SaleRepository saleRepository) {
        this.itemRepository = itemRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public List<WishListItemDTO> getWishList(Long customerId) {
        List<Object[]> results = itemRepository.findWishListByCustomerId(customerId);
        return results.stream()
                .map(result -> {
                    WishListItemDTO itemDTO = new WishListItemDTO();
                    itemDTO.setItemId((Long) result[0]);
                    itemDTO.setItemDescription((String) result[1]);
                    itemDTO.setCustomerId((Long) result[2]);
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
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        return saleRepository.findMaxSaleDay(startDateTime, endDateTime);
    }

    @Override
    public List<TopSellingItemDTO> getTopSellingItemsAllTime() {
        return itemRepository.findTopSellingItems().stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public List<TopSellingItemDTO> getTopSellingItemsLastMonth() {
        LocalDate currentDate = LocalDate.now();
        LocalDate lastMonthDate = currentDate.minusMonths(1);

        int lastMonth = lastMonthDate.getMonthValue();
        int lastMonthYear = lastMonthDate.getYear();

        return itemRepository.findTopSellingItemsForMonth(lastMonth, lastMonthYear);
    }
}
