package com.example.LeaveManagement.users.UserController;

import com.example.LeaveManagement.users.Dto.UserDto;
import com.example.LeaveManagement.users.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    private final UserDto userDto;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserDto userDto, BCryptPasswordEncoder passwordEncoder) {
        this.userDto = userDto;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody UserDto userDto) throws JsonProcessingException {
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encryptedPassword);
        int id = userService.addUser(userDto);
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString("User is registered");
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
    }

/*    @PutMapping("/employees/")
    public ResponseEntity<Employee> updateEmployeeManager(@RequestBody EmployeeUpdateRequest request) throws Exception {
        Employee updatedEmployee = userService.updateEmployee(request.getEmployeeId(), request.getManagerId());
        return ResponseEntity.ok().body(updatedEmployee);
    }*/


    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) throws JsonProcessingException {

        UserDto existingUser = userService.getUserByUsername(userDto.getUsername());
        ObjectMapper objectMapper = new ObjectMapper();
        if (existingUser != null && passwordEncoder.matches(userDto.getPassword(), existingUser.getPassword())) {
            if (existingUser.getRole().equals("Admin")) {
                String response = objectMapper.writeValueAsString("Admin User");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
            } else if (existingUser.getRole().equals("Manger")) {
                String response = objectMapper.writeValueAsString("Invalid username or password");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
            } else if (existingUser.getRole().equals("Employee")) {
                String response = objectMapper.writeValueAsString("Invalid username or password");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(response);
            }
        }
        return null;
    }






}












