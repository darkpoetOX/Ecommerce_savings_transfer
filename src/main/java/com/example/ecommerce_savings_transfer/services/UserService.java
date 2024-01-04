package com.example.ecommerce_savings_transfer.services;

import com.example.ecommerce_savings_transfer.models.User;
import com.example.ecommerce_savings_transfer.repositories.ShoppingRepository;
import com.example.ecommerce_savings_transfer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Long userId, User updatedUser) {
        // Additional logic/validation can be added here before updating
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            // Perform updates based on your requirements
            User userToUpdate = existingUser.get();
            userToUpdate.setName(updatedUser.getName());
            userToUpdate.setShoppingList(updatedUser.getShoppingList());

            return userRepository.save(userToUpdate);
        } else {
            // Handle case where the user with the given ID is not found
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    public void deleteUser(Long userId) {
        // Additional logic/validation can be added here before deleting
        userRepository.deleteById(userId);
    }

}
