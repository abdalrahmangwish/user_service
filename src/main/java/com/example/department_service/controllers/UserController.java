package com.example.department_service.controllers;

import com.example.department_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/departments")
public class UserController {

    @Autowired
    private UserService departmentService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getById(id), HttpStatus.OK);
    }
}
