package com.example.ecommerce_savings_transfer.controllers;

import com.example.ecommerce_savings_transfer.models.BankAccount;
import com.example.ecommerce_savings_transfer.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bank-accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long accountId, @RequestBody BankAccount updatedBankAccount) {
        BankAccount updatedAccount = bankAccountService.updateBankAccount(accountId, updatedBankAccount);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long accountId) {
        bankAccountService.deleteBankAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
