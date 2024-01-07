package com.example.ecommerce_savings_transfer.controllers;

import com.example.ecommerce_savings_transfer.models.BankAccount;
import com.example.ecommerce_savings_transfer.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bank-accounts")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping //GET /bank-accounts
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountService.getAllBankAccounts();
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @GetMapping("/{id}") //GET /bank-accounts/{id}
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        Optional<BankAccount> optionalBankAccount = bankAccountService.getBankAccountById(id);

        if (optionalBankAccount.isPresent()) {
            return new ResponseEntity<>(optionalBankAccount.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping //POST /bank-accounts
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);
        return new ResponseEntity<>(createdBankAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") //PUT /bank-accounts/{id}
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount updatedBankAccount) {
        try {
            BankAccount updatedAccount = bankAccountService.updateBankAccount(id, updatedBankAccount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") //DELETE /bank-accounts/{id}
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        try {
            bankAccountService.deleteBankAccount(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

