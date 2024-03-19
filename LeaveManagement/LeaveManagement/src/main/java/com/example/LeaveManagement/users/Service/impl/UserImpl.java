package com.example.LeaveManagement.users.Service.impl;

import com.example.LeaveManagement.users.Repo.UserRepo;
import com.example.LeaveManagement.users.Dto.UserDto;
import com.example.LeaveManagement.users.UserEntity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.LeaveManagement.users.Service.UserService;

import java.util.List;

@Service
public class UserImpl implements UserService {


    @Autowired
    private UserRepo userRepo;


    @Override
    public int addUser(UserDto userDto) {
        User user=new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());

      userRepo.save(user);


      return user.getUserid();
    }
    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        userRepo.findAll();
        if (user != null) {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());// Note: You might not want to send password in the DTO in a real-world scenario
            // Set other properties as needed
            return userDto;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<User> findUsernamesWithRole(String role) {
        return null;
    }
}









