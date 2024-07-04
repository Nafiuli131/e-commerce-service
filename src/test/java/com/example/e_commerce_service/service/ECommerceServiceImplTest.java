package com.example.e_commerce_service.service;

import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.repository.ItemRepository;
import com.example.e_commerce_service.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ECommerceServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private ECommerceServiceImpl eCommerceService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWishList() {

        Long customerId = 1L;
        Object[] item1 = {1L, "Item 1", 1L, 100.0, 1L, "Item One"};
        Object[] item2 = {2L, "Item 2", 1L, 200.0, 2L, "Item Two"};
        List<Object[]> mockResults = Arrays.asList(item1, item2);

        when(itemRepository.findWishListByCustomerId(customerId)).thenReturn(mockResults);

        List<WishListItemDTO> result = eCommerceService.getWishList(customerId);

        assertEquals(2, result.size());
        assertEquals("Item 1", result.get(0).getItemDescription());
        assertEquals(200.0, result.get(1).getItemPrice());
    }

    @Test
    public void testGetTotalSaleAmountCurrentDay() {

        Double expectedTotal = 500.0;
        when(itemRepository.calculateTotalSaleAmountCurrentDay()).thenReturn(expectedTotal);

        Double result = eCommerceService.getTotalSaleAmountCurrentDay();

        assertEquals(expectedTotal, result);
    }

    @Test
    public void testGetMaxSaleDay() {

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        LocalDate expectedMaxSaleDay = LocalDate.of(2023, 10, 15); // Example date

        when(saleRepository.findMaxSaleDay(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)))
                .thenReturn(expectedMaxSaleDay);

        LocalDate result = eCommerceService.getMaxSaleDay(startDate, endDate);

        assertEquals(expectedMaxSaleDay, result);
    }

    @Test
    public void testGetTopSellingItemsAllTime() {

        TopSellingItemDTO item1 = new TopSellingItemDTO(1L, "Item 1", "description 1", 300.00);
        TopSellingItemDTO item2 = new TopSellingItemDTO(2L, "Item 2", "description 2", 800.00);
        List<TopSellingItemDTO> expectedTopItems = Arrays.asList(item1, item2);

        when(itemRepository.findTopSellingItems()).thenReturn(expectedTopItems);

        List<TopSellingItemDTO> result = eCommerceService.getTopSellingItemsAllTime();

        assertEquals(expectedTopItems.size(), result.size());
        assertEquals("Item 1", result.get(0).getName());
        assertEquals(800.0, result.get(1).getTotalSaleAmount());
    }

    @Test
    public void testGetTopSellingItemsLastMonth() {

        TopSellingItemDTO item1 = new TopSellingItemDTO(1L, "Item 1", "description 1", 300.00);
        TopSellingItemDTO item2 = new TopSellingItemDTO(2L, "Item 2", "description 2", 400.00);
        List<TopSellingItemDTO> expectedTopItems = Arrays.asList(item1, item2);

        when(itemRepository.findTopSellingItemsLastMonth()).thenReturn(expectedTopItems);

        List<TopSellingItemDTO> result = eCommerceService.getTopSellingItemsLastMonth();

        assertEquals(expectedTopItems.size(), result.size());
        assertEquals("Item 1", result.get(0).getName());
        assertEquals(400.0, result.get(1).getTotalSaleAmount());
    }
}

