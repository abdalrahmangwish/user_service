package com.example.department_service.controllers;

import com.example.department_service.entities.User;
import com.example.department_service.repositories.UserRepo;
import com.example.department_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @GetMapping("")
    public ResponseEntity<?> getUserById() {
        System.out.println("dsgsgdsgdsgd");
        List<User> user = userRepo.findAll();
        return ResponseEntity.ok(user);
    }
    // Other CRUD operations can be added here
}