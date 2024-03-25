package com.example.LeaveManagement.controller;

import com.example.LeaveManagement.dto.UserDTO;
import com.example.LeaveManagement.entity.User;
import com.example.LeaveManagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody UserDTO userDTO){
        return userService.registerNewUser(userDTO);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String forUser(){

        return "This URL is only accessible to the user";
    }
}
