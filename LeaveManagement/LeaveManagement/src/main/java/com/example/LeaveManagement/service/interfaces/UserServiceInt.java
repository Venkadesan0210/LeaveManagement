package com.example.leavemanagement.service.interfaces;
import com.example.leavemanagement.dto.UserDTO;
import com.example.leavemanagement.entity.User;
import java.util.List;
public interface UserServiceInt {
    public void initRoleAndUser();
    public User registerNewUser(UserDTO userDTO);
    public List<User> getAllUsers();
    public String getEncodedPassword(String password);
    public User updateUserAssignee(String userName, String managerUserName);
}
