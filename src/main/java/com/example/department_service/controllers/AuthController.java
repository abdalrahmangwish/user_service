package com.example.department_service.controllers;

import com.example.department_service.core.base_service.JwtUtil;
import com.example.department_service.core.base_service.MyUserDetailsService;
import com.example.department_service.dto.AuthResponse;
import com.example.department_service.entities.User;
import com.example.department_service.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Authentication failed: " + e.getMessage());
        }

        // Authentication successful, generate JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        // Fetch user details from the repository
        com.example.department_service.entities.User fullUserDetails = userRepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + user.getUsername()));

        // Create a custom response object containing user information and token
        AuthResponse authResponse = new AuthResponse(
                fullUserDetails.getUsername(),
                jwt,
                fullUserDetails.getEmail(),
                fullUserDetails.getRole(),
                fullUserDetails.getBio(),
                fullUserDetails.getDescription()
        );

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody com.example.department_service.entities.User user) {
        // Save user details (implement this method in your MyUserDetailsService)
        userDetailsService.save(user);
        return "User registered successfully";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return user.toString();
    }
}
