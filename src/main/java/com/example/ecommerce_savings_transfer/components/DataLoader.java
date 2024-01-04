package com.example.ecommerce_savings_transfer.components;
import com.example.ecommerce_savings_transfer.models.BankAccount;
import com.example.ecommerce_savings_transfer.models.Shopping;
import com.example.ecommerce_savings_transfer.models.User;
import com.example.ecommerce_savings_transfer.services.BankAccountService;
import com.example.ecommerce_savings_transfer.services.ShoppingService;
import com.example.ecommerce_savings_transfer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;



@Component
public class DataLoader implements CommandLineRunner {
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final ShoppingService shoppingService;

    @Autowired
    public DataLoader(UserService userService, BankAccountService bankAccountService, ShoppingService shoppingService) {
        this.userService = userService;
        this.bankAccountService = bankAccountService;
        this.shoppingService = shoppingService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create sample user
        User user = new User();
        user.setName("John Doe");

        // Create sample bank account
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountType("Savings");
        bankAccount.setSecurityNumber("123");
        bankAccount.setPinNumber("5678");
        bankAccount.setAccountNumber("123456789");
        bankAccount.setCurrentAccountBalance(1000.0);
        bankAccount.setSavingsAccountBalance(500.0);
        user.setBankAccount(bankAccount);

        // Create sample shopping items
        List<Shopping> shoppingList = Arrays.asList(
                new Shopping("Item1", LocalDateTime.now(), 150.0, true),
                new Shopping("Item2", LocalDateTime.now(), 80.0, false)
        );
        user.setShoppingList(shoppingList);

        // Save entities
        userService.createUser(user);
        bankAccountService.createBankAccount(bankAccount);

        // Process shopping items
        shoppingService.processShoppingItems(user.getShoppingList(), user.getBankAccount());
    }
}