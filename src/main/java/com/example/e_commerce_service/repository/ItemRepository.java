package com.example.e_commerce_service.repository;

import com.example.e_commerce_service.dto.TopSellingItemDTO;
import com.example.e_commerce_service.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select item_id,description," +
            "customer_id,price,wish_list.id,item.name from item join wish_list on item.id=wish_list.item_id join customer \n" +
            "on customer.id = wish_list.customer_id where customer.id = :customerId", nativeQuery = true)
    List<Object[]> findWishListByCustomerId(Long customerId);

    @Query("SELECT COALESCE(SUM(s.saleAmount), 0) FROM Sale s WHERE DATE(s.saleDate) = CURRENT_DATE")
    Double calculateTotalSaleAmountCurrentDay();


    @Query("SELECT new com.example.e_commerce_service.dto.TopSellingItemDTO(s.item.id, " +
            "s.item.name, " +
            "s.item.description, " +
            "SUM(s.saleAmount)) " +
            "FROM Sale s " +
            "GROUP BY s.item.id " +
            "ORDER BY SUM(s.saleAmount) DESC")
    List<TopSellingItemDTO> findTopSellingItems();

    @Query("SELECT new com.example.e_commerce_service.dto.TopSellingItemDTO(s.item.id, " +
            "s.item.name, " +
            "s.item.description, " +
            "SUM(s.saleAmount)) " +
            "FROM Sale s " +
            "WHERE MONTH(s.saleDate) = :month AND YEAR(s.saleDate) = :year " +
            "GROUP BY s.item.id " +
            "ORDER BY SUM(s.saleAmount) DESC")
    List<TopSellingItemDTO> findTopSellingItemsForMonth(@Param("month") int month, @Param("year") int year);

}
