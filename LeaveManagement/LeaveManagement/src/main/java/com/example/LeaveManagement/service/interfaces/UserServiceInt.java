package com.example.leavemanagement.service.interfaces;
import com.example.leavemanagement.dto.UserDTO;
import com.example.leavemanagement.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserServiceInt {
    public void initRoleAndUser();
    public ResponseEntity<User> registerNewUser(UserDTO userDTO);
    public List<User> getAllUsers();
    public String getEncodedPassword(String password);
    public User updateUserAssignee(String userName, String managerUserName, String secondaryManagerUserName);

    boolean deleteUser(String userName);
    public ResponseEntity<String> changePassword(Map<String,String> requestMap);

}
