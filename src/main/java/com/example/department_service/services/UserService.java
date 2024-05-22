package com.example.department_service.services;

import com.example.department_service.entities.User;
import com.example.department_service.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo departmentRepository;

    public List<User> getAll(){

        return departmentRepository.findAll();
    }

    public User getById(Integer id){
         Optional<User> department= departmentRepository.findById(id);
        return department.orElse(null);
    }


}
