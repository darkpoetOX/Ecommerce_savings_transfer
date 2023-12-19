package com.example.ecommerce_savings_transfer.services;

import com.example.ecommerce_savings_transfer.models.Shopping;
import com.example.ecommerce_savings_transfer.repositories.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {
    private final ShoppingRepository shoppingRepository;

    @Autowired
    public ShoppingService(ShoppingRepository shoppingRepository) {

        this.shoppingRepository = shoppingRepository;
    }

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

    public void deleteShoppingItem(Long itemId) {
        shoppingRepository.deleteById(itemId);
    }
}