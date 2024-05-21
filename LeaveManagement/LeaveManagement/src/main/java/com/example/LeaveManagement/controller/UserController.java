package com.example.leavemanagement.controller;
import com.example.leavemanagement.dto.ResetPasswordRequestDto;
import com.example.leavemanagement.dto.UserDTO;
import com.example.leavemanagement.entity.User;
import com.example.leavemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.leavemanagement.dto.UpdateUserAssigneeDto;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/registerNewUser"})

    public ResponseEntity<User> registerNewUser(@RequestBody UserDTO userDTO) {
        return userService.registerNewUser(userDTO);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String forUser() {
        return "This URL is only accessible to the user";
    }

    @GetMapping("/allusers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/update-user")
    public User updateManagerForUser(@RequestBody UpdateUserAssigneeDto updateUserAssignee) {
        return userService.updateUserAssignee(updateUserAssignee.getUserName(), updateUserAssignee.getManagerUserName(), updateUserAssignee.getSecondarymanagerUserName());
    }


    @GetMapping("/assigneeByEmployee")
    public List<User> assigneeByEmployee(@RequestParam String assigneeByEmployee) {
        return userService.assigneeScondaryByEmployee(assigneeByEmployee);
    }

    @DeleteMapping("/user/delete/{userName}")
    public boolean deleteUser(@PathVariable String userName){
        return userService.deleteUser(userName);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String,String> requestMap) {
        try {
            return userService.changePassword(requestMap);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"subject\": \"" + "Error occurred while changing password" + "\"}");
        }
    }
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> requestMap) {
        String email = requestMap.get("email");

        try {
            return userService.forgotPassword(email);
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"subject\": \"" + "Error occurred while processing forgot password request" + "\"}");
        }
    }
    @PostMapping({"/reset"})
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDto resetPasswordRequest) {
        String email = resetPasswordRequest.getEmail();
        String otp = resetPasswordRequest.getOtp();
        String newPassword = resetPasswordRequest.getNewPassword();
        return userService.resetPassword(email, otp, newPassword);
    }

    }



