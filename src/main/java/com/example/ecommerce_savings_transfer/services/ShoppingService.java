package com.example.ecommerce_savings_transfer.services;

import com.example.ecommerce_savings_transfer.models.Shopping;
import com.example.ecommerce_savings_transfer.repositories.ShoppingRepository;
import com.example.ecommerce_savings_transfer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {

    @Autowired
    ShoppingRepository shoppingRepository;


    public List<Shopping> getAllShoppingItems() {

        return shoppingRepository.findAll();
    }

    public Optional<Shopping> getShoppingItemById(Long itemId) {
        return shoppingRepository.findById(itemId);
    }

    public Shopping createShoppingItem(Shopping shoppingItem) {
        return shoppingRepository.save(shoppingItem);
    }

    public Shopping updateShoppingItem(Long itemId, Shopping updatedShoppingItem) {
        Optional<Shopping> existingItem = shoppingRepository.findById(itemId);
        if (existingItem.isPresent()) {
            Shopping itemToUpdate = existingItem.get();
            itemToUpdate.setItemName(updatedShoppingItem.getItemName());
            itemToUpdate.setTransactionDateTime(updatedShoppingItem.getTransactionDateTime());
            itemToUpdate.setPrice(updatedShoppingItem.getPrice());
            itemToUpdate.setIsNeed(updatedShoppingItem.isNeed());

            return shoppingRepository.save(itemToUpdate);
        } else {
            // Handle case where the shopping item with the given ID is not found
            throw new IllegalArgumentException("Shopping item with ID " + itemId + " not found");
        }
    }

    public void processShoppingItems(List<Shopping> shoppingItems, BankAccount bankAccount) {
        for (Shopping item : shoppingItems) {
            double roundedPrice = item.isNeed() ? roundToNearestDollar(item.getPrice()) : roundToNearestTenDollars(item.getPrice());

            // Calculate the difference and update account balances
            double difference = roundedPrice - item.getPrice();
            bankAccount.decrementCurrentAccount(difference);
            bankAccount.incrementSavingsAccount(difference);

            // Other actions as needed...
        }
    }

    private double roundToNearestDollar(double amount) {
        return Math.round(amount);
    }

    private double roundToNearestTenDollars(double amount) {
        return Math.round(amount / 10.0) * 10.0;
    }

    public void deleteShoppingItem(Long itemId) {
        shoppingRepository.deleteById(itemId);
    }
}