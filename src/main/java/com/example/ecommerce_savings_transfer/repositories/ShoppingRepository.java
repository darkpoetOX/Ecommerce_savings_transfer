package com.example.ecommerce_savings_transfer.repositories;

import com.example.ecommerce_savings_transfer.models.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingRepository extends JpaRepository<Shopping, Long> {
}