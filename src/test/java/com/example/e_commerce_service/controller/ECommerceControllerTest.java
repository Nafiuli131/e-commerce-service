package com.example.e_commerce_service.controller;

import com.example.e_commerce_service.dto.WishListItemDTO;
import com.example.e_commerce_service.service.ECommerceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ECommerceControllerTest {

    @Mock
    private ECommerceService eCommerceService;

    @InjectMocks
    private ECommerceController eCommerceController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(eCommerceController).build();
    }

    @Test
    public void testGetWishList() throws Exception {
        WishListItemDTO item1 = new WishListItemDTO();
        item1.setItemId(1L);
        item1.setItemDescription("Mock Item 1");

        WishListItemDTO item2 = new WishListItemDTO();
        item2.setItemId(2L);
        item2.setItemDescription("Mock Item 2");

        List<WishListItemDTO> mockWishList = Arrays.asList(item1, item2);
        when(eCommerceService.getWishList(any())).thenReturn(mockWishList);

        mockMvc.perform(get("/api/ecommerce/wishlist/{customerId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].itemId").value(item1.getItemId()))
                .andExpect(jsonPath("$[0].itemDescription").value(item1.getItemDescription()))
                .andExpect(jsonPath("$[1].itemId").value(item2.getItemId()))
                .andExpect(jsonPath("$[1].itemDescription").value(item2.getItemDescription()));
    }

    @Test
    public void testGetTotalSaleAmountCurrentDay() throws Exception {
        Double mockTotalSaleAmount = 100.0;
        when(eCommerceService.getTotalSaleAmountCurrentDay()).thenReturn(mockTotalSaleAmount);
        mockMvc.perform(get("/api/ecommerce/totalsale/current"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(mockTotalSaleAmount));
    }

    @Test
    public void testConnectionToMaxSaleDayEndpoint() throws Exception {
        mockMvc.perform(post("/api/ecommerce/maxsaleday")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startDate\": \"2023-01-01\", \"endDate\": \"2023-12-31\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTopSellingItemsAllTime() throws Exception {
        mockMvc.perform(get("/api/ecommerce/topselling/alltime"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetTopSellingItemsLastMonth() throws Exception {
        mockMvc.perform(get("/api/ecommerce/topselling/lastmonth"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }


}
