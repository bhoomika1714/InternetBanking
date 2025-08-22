package com.bankingapp.controller;



import com.bankingapp.model.User;
import com.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // TODO: Add password hashing later
        return userRepository.save(user);
    }
}
