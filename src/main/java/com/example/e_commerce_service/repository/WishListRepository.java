package com.example.e_commerce_service.repository;

import com.example.e_commerce_service.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Long>{

}
