package com.example.leavemanagement.controller;
import com.example.leavemanagement.dto.UserDTO;
import com.example.leavemanagement.entity.User;
import com.example.leavemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.leavemanagement.dto.UpdateUserAssigneeDto;
import java.util.List;

@RestController
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
    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/update-user")
    public User updateManagerForUser(@RequestBody UpdateUserAssigneeDto updateUserAssignee){
        return userService.updateUserAssignee(updateUserAssignee.getUserName(),updateUserAssignee.getManagerUserName());
    }
}
