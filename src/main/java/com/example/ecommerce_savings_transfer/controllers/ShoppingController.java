package com.example.ecommerce_savings_transfer.controllers;

import com.example.ecommerce_savings_transfer.models.Shopping;
import com.example.ecommerce_savings_transfer.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shoppings")
public class ShoppingController {

    private final ShoppingService shoppingService;

    @Autowired
    public ShoppingController(ShoppingService shoppingService)
    {
        this.shoppingService = shoppingService;
    }
    @PostMapping
    public ResponseEntity<Shopping> createShoppingItem(@RequestBody Shopping shoppingItem) {
        Shopping createdShoppingItem = shoppingService.createShoppingItem(shoppingItem);
        return new ResponseEntity<>(createdShoppingItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Shopping>> getAllShoppingItems() {
        List<Shopping> shoppingItems = shoppingService.getAllShoppingItems();
        return new ResponseEntity<>(shoppingItems, HttpStatus.OK);
    }

}