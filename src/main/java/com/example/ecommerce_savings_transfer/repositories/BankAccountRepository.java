package com.example.ecommerce_savings_transfer.repositories;

import com.example.ecommerce_savings_transfer.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
