package com.example.LeaveManagement.users.Service;

import com.example.LeaveManagement.users.Dto.UserDto;
import com.example.LeaveManagement.users.UserEntity.User;

import java.util.List;

public interface UserService {

    int addUser(UserDto userDto);


    UserDto getUserByUsername(String username);

    List<User> getAllUsers();

    List<User> findUsernamesWithRole(String role);
  //  Employee updateEmployee(Long employeeId,Long managerId) throws Exception;

}