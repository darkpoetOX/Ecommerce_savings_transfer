package com.example.ecommerce_savings_transfer.services;

import com.example.ecommerce_savings_transfer.models.BankAccount;
import com.example.ecommerce_savings_transfer.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        // Additional logic/validation can be added here before saving
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount updateBankAccount(Long accountId, BankAccount updatedBankAccount) {
        // Additional logic/validation can be added here before updating
        Optional<BankAccount> existingAccount = bankAccountRepository.findById(accountId);
        if (existingAccount.isPresent()) {
            // May not need all of these updates
            BankAccount accountToUpdate = existingAccount.get();
            accountToUpdate.setAccountType(updatedBankAccount.getAccountType());
            accountToUpdate.setSecurityNumber(updatedBankAccount.getSecurityNumber());
            accountToUpdate.setPinNumber(updatedBankAccount.getPinNumber());
            accountToUpdate.setAccountNumber(updatedBankAccount.getAccountNumber());
            accountToUpdate.setCurrentAccountBalance(updatedBankAccount.getCurrentAccountBalance());
            accountToUpdate.setSavingsAccountBalance(updatedBankAccount.getSavingsAccountBalance());

            return bankAccountRepository.save(accountToUpdate);
        } else {
            // Handle case where the bank account with the given ID is not found
            throw new IllegalArgumentException("Bank account with ID " + accountId + " not found");
        }
    }

    public void deleteBankAccount(Long accountId) {
        // Additional logic/validation can be added here before deleting
        bankAccountRepository.deleteById(accountId);
    }
}
