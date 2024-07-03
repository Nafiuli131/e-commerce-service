package com.example.e_commerce_service.controller;

import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.entity.Item;
import com.example.e_commerce_service.service.ECommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ecommerce")
public class ECommerceController {

    private final ECommerceService eCommerceService;

    @Autowired
    public ECommerceController(ECommerceService eCommerceService) {
        this.eCommerceService = eCommerceService;
    }

    @GetMapping("/wishlist/{customerId}")
    public ResponseEntity<?> getWishList(@PathVariable Long customerId) {
        List<WishListItemDTO> wishList = eCommerceService.getWishList(customerId);
        return ResponseEntity.ok(wishList);
    }

    @GetMapping("/totalsale/current")
    public ResponseEntity<?> getTotalSaleAmountCurrentDay() {
        Double totalSaleAmount = eCommerceService.getTotalSaleAmountCurrentDay();
        return ResponseEntity.ok(totalSaleAmount);
    }

    @GetMapping("/maxsaleday")
    public ResponseEntity<LocalDate> getMaxSaleDay(
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        LocalDate maxSaleDay = eCommerceService.getMaxSaleDay(startDate, endDate);
        return ResponseEntity.ok(maxSaleDay);
    }


    @GetMapping("/topselling/alltime")
    public ResponseEntity<?> getTopSellingItemsAllTime() {
        List<TopSellingItemDTO> topSellingItems = eCommerceService.getTopSellingItemsAllTime();
        return ResponseEntity.ok(topSellingItems);
    }

    @GetMapping("/topselling/lastmonth")
    public ResponseEntity<?> getTopSellingItemsLastMonth() {
        List<Item> topSellingItems = eCommerceService.getTopSellingItemsLastMonth();
        return ResponseEntity.ok(topSellingItems);
    }
}

