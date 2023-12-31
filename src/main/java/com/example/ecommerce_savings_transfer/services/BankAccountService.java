package com.example.ecommerce_savings_transfer.services;

import com.example.ecommerce_savings_transfer.models.BankAccount;
import com.example.ecommerce_savings_transfer.models.Shopping;
import com.example.ecommerce_savings_transfer.repositories.BankAccountRepository;
import com.example.ecommerce_savings_transfer.repositories.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(BankAccount bankAccount) {
        // Additional logic/validation can be added here before saving
        return bankAccountRepository.save(bankAccount);
    }
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Long accountId) {
        return bankAccountRepository.findById(accountId);
    }
    public BankAccount updateBankAccount(Long accountId, BankAccount updatedBankAccount) {
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

    public BankAccount processShoppingItems(List<Shopping> shoppingItems, Long accountId) {
        Optional<BankAccount> optionalAccount = bankAccountRepository.findById(accountId);

        if (optionalAccount.isPresent()) {
            BankAccount accountToUpdate = optionalAccount.get();

            for (Shopping item : shoppingItems) {
                double roundedPrice = item.isNeed() ? roundToNearestDollar(item.getPrice()) : roundToNearestTenDollars(item.getPrice());

                // Calculate the difference and update account balances
                double difference = roundedPrice - item.getPrice();
                accountToUpdate.decrementCurrentAccount(difference);
                accountToUpdate.incrementSavingsAccount(difference);
            }

            // Save the updated account and return it
            return bankAccountRepository.save(accountToUpdate);
        } else {
            // Handle case where the bank account with the given ID is not found
            throw new IllegalArgumentException("Bank account with ID " + accountId + " not found");
        }
    }

    private double roundToNearestDollar(double amount) {
        double roundedPrice = Math.round(amount);
        return (roundedPrice - amount) == 0 ? 1.0 + amount : roundedPrice;
    }

    private double roundToNearestTenDollars(double amount) {
        double roundedPrice = Math.round(amount / 10.0) * 10.0;
        return (roundedPrice - amount) == 0 ? 5.0 + amount : roundedPrice;
    }


    public void deleteBankAccount(Long accountId) {
        // Additional logic/validation can be added here before deleting
        bankAccountRepository.deleteById(accountId);
    }
}
