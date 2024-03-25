package com.example.LeaveManagement.service.interfaces;
import com.example.LeaveManagement.dto.UserDTO;
import com.example.LeaveManagement.entity.User;

import java.util.List;

public interface UserServiceInt {
    public void initRoleAndUser();

    public User registerNewUser(UserDTO userDTO);

    public List<User> getAllUsers();

    public String getEncodedPassword(String password);

    public User updateUserAssignee(String userName, String managerUserName);
}
