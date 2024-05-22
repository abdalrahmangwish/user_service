package com.example.department_service.dto;

 public class AuthResponse {
    private String username;
    private String token;
    private String email;
    private String role;
    private String bio;
    private String description;

    public AuthResponse(String username, String token, String email, String role, String bio, String description) {
        this.username = username;
        this.token = token;
        this.email = email;
        this.role = role;
        this.bio = bio;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getBio() {
        return bio;
    }

    public String getDescription() {
        return description;
    }
}