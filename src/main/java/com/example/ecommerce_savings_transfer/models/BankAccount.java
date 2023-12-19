package com.example.ecommerce_savings_transfer.models;


import jakarta.persistence.*;
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountType;
    private String securityNumber;
    private String pinNumber;
    private String accountNumber;
    private double currentAccountBalance;
    private double savingsAccountBalance;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getCurrentAccountBalance() {
        return currentAccountBalance;
    }

    public void setCurrentAccountBalance(double currentAccountBalance) {
        this.currentAccountBalance = currentAccountBalance;
    }

    public double getSavingsAccountBalance() {
        return savingsAccountBalance;
    }

    public void setSavingsAccountBalance(double savingsAccountBalance) {
        this.savingsAccountBalance = savingsAccountBalance;
    }
}