package com.example.e_commerce_service.controller;

import com.example.e_commerce_service.dto.DateRangeRequest;
import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.service.ECommerceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ecommerce")
public class ECommerceController {

    private static final Logger logger = LoggerFactory.getLogger(ECommerceController.class);

    private final ECommerceService eCommerceService;

    @Autowired
    public ECommerceController(ECommerceService eCommerceService) {
        this.eCommerceService = eCommerceService;
    }

    @Operation(summary = "Get wish list", description = "Retrieve the wish list for a specific customer by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the wish list"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/wishlist/{customerId}")
    public ResponseEntity<?> getWishList(@PathVariable Long customerId) {
        logger.info("Fetching wish list for customer with ID: {}", customerId);
        List<WishListItemDTO> wishList = eCommerceService.getWishList(customerId);
        return ResponseEntity.ok(wishList);
    }

    @Operation(summary = "Get total sale amount for the current day", description = "Retrieve the total sale amount for the current day")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the total sale amount"),
    })
    @GetMapping("/totalsale/current")
    public ResponseEntity<?> getTotalSaleAmountCurrentDay() {
        logger.info("Fetching total sale amount for the current day");
        Double totalSaleAmount = eCommerceService.getTotalSaleAmountCurrentDay();
        return ResponseEntity.ok(totalSaleAmount);
    }

    @Operation(summary = "Get maximum sale day", description = "Retrieve the day with the highest sales within a specified date range")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the maximum sale day"),
            @ApiResponse(responseCode = "400", description = "Invalid date range provided")
    })
    @PostMapping("/maxsaleday")
    public ResponseEntity<LocalDate> getMaxSaleDay(@RequestBody DateRangeRequest dateRangeRequest) {
        logger.info("Fetching maximum sale day for date range: {} to {}", dateRangeRequest.getStartDate(), dateRangeRequest.getEndDate());
        LocalDate maxSaleDay = eCommerceService.getMaxSaleDay(dateRangeRequest.getStartDate(), dateRangeRequest.getEndDate());
        return ResponseEntity.ok(maxSaleDay);
    }

    @Operation(summary = "Get top selling items of all time", description = "Retrieve the top 5 selling items of all time based on the total sale amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the top selling items"),
    })
    @GetMapping("/topselling/alltime")
    public ResponseEntity<?> getTopSellingItemsAllTime() {
        logger.info("Fetching top selling items of all time");
        List<TopSellingItemDTO> topSellingItems = eCommerceService.getTopSellingItemsAllTime();
        return ResponseEntity.ok(topSellingItems);
    }

    @Operation(summary = "Get top selling items of the last month", description = "Retrieve the top 5 selling items of the last month based on the total sale amount")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the top selling items"),
    })
    @GetMapping("/topselling/lastmonth")
    public ResponseEntity<?> getTopSellingItemsLastMonth() {
        logger.info("Fetching top selling items of the last month");
        List<TopSellingItemDTO> topSellingItems = eCommerceService.getTopSellingItemsLastMonth();
        return ResponseEntity.ok(topSellingItems);
    }
}
