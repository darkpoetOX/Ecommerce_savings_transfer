package com.example.ecommerce_savings_transfer.repositories;

import com.example.ecommerce_savings_transfer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
