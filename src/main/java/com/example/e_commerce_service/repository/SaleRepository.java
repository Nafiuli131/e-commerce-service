package com.example.e_commerce_service.repository;
import com.example.e_commerce_service.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT MAX(DATE(s.saleDate)) FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    LocalDate findMaxSaleDay(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
