package com.example.e_commerce_service.repository;

import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select item_id,description," +
            "customer_id,price,wish_list.id,item.name from item join wish_list on item.id=wish_list.item_id join customer \n" +
            "on customer.id = wish_list.customer_id where customer.id = :customerId",nativeQuery = true)
    List<Object[]> findWishListByCustomerId(Long customerId);

    @Query("SELECT COALESCE(SUM(s.saleAmount), 0) FROM Sale s WHERE DATE(s.saleDate) = CURRENT_DATE")
    Double calculateTotalSaleAmountCurrentDay();

    @Query("SELECT MAX(s.saleDate) FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    LocalDate findMaxSaleDay(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.example.e_commerce_service.dto.TopSellingItemDTO(s.item.id, " +
            "SUM(s.saleAmount)) " +
            "FROM Sale s " +
            "GROUP BY s.item.id " +
            "ORDER BY SUM(s.saleAmount) DESC")
    List<TopSellingItemDTO> findTopSellingItems();
//
//    @Query("SELECT i FROM Item i WHERE MONTH(i.saleDate) = MONTH(CURRENT_DATE) AND YEAR(i.saleDate) = YEAR(CURRENT_DATE) ORDER BY i.quantitySold DESC")
//    List<Item> findTopSellingItemsLastMonth();
}
