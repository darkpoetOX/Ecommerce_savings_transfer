package com.example.ecommerce_savings_transfer.models;

import com.example.ecommerce_savings_transfer.models.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shoppings")
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private LocalDateTime transactionDateTime;
    private double price;
    private boolean isNeed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNeed() {
        return isNeed;
    }

    public void setIsNeed(boolean need) {
        isNeed = need;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
