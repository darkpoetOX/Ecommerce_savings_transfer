package com.example.ecommerce_savings_transfer.services;

import com.example.ecommerce_savings_transfer.models.Shopping;
import com.example.ecommerce_savings_transfer.repositories.ShoppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}