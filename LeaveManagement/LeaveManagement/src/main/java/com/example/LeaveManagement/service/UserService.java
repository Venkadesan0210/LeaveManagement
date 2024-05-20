package com.example.leavemanagement.service;
import com.example.leavemanagement.entity.*;
import com.example.leavemanagement.repo.LeaveRequestRepo;
import com.example.leavemanagement.repo.RoleRepo;
import com.example.leavemanagement.repo.UserRepo;
import com.example.leavemanagement.dto.UserDTO;
import com.example.leavemanagement.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserService implements UserServiceInt {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final LeaveRequestRepo leaveRequestRepo;
    private final MailService mailService;
    private static final String SUBJECT_PREFIX = "{\"subject\": \"";
    private static final String EMAIL_NOT_FOUND_MESSAGE = SUBJECT_PREFIX + "Email not found\"}";

    @Autowired
    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo,
                       PasswordEncoder passwordEncoder,
                       LeaveRequestRepo leaveRequestRepo,
                       MailService mailService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.leaveRequestRepo = leaveRequestRepo;
        this.mailService = mailService;
    }
    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepo.save(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleRepo.save(userRole);
    }
    public ResponseEntity<User> registerNewUser(UserDTO userDTO) {
        User user = new User();
        User existingUser = userRepo.findByUserName(userDTO.getUserName());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // Populate user data and save
        Role role = roleRepo.findByRoleName(userDTO.getUserRole());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setUserName(userDTO.getUserName());
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(userDTO.getUserPassword()));
        user.setUserRole(role.getRoleName());
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setUserLastName(userDTO.getUserLastName());
        if (role.getRoleName().equals("EMPLOYEE")) {
            EmployeeLeave employeeLeave = new EmployeeLeave();
            employeeLeave.setMarriageLeave(3);
            employeeLeave.setMedicalLeave(10);
            employeeLeave.setPrivilegeLeave(10);
            employeeLeave.setPaternityLeave(5);
            user.setEmployeeLeave(employeeLeave);
        }
        // Save the user
        User savedUser = userRepo.save(user);

        // Return ResponseEntity with CREATED status code and saved user
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
        @Transactional
        public boolean deleteUser(String userName) {
            User user = userRepo.findByUserName(userName);
            if (user != null) {
                if(user.getEmployeeLeave()!=null){
                    List<LeaveRequests> leaveRequests = leaveRequestRepo.findByEmployeeLeaveId(user.getEmployeeLeave().getId());
                    leaveRequestRepo.deleteAll(leaveRequests);
                }
                user.setRole(Collections.emptySet());
                userRepo.deleteById(userName);
                return true;
            } else {
                return false; // User not found
            }
        }

    public ResponseEntity<String> forgotPassword(String email) {
        // Check if the email exists in the database
        User user = userRepo.findByUserName(email);
        if (user == null) {
            // Define constant for error message
            final String errorMessage = EMAIL_NOT_FOUND_MESSAGE;

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorMessage);
        }

        // Generate OTP
        String otp = generateOTP();

        // Store the OTP with the user's email
        user.setOtp(otp);
       // Set OTP expiry time, e.g., 15 minutes from now
        userRepo.save(user);

        // Send the OTP via email
        sendPasswordResetOTP(email, otp);

        String otpSentMessage = SUBJECT_PREFIX + "OTP sent successfully\"}";
        return ResponseEntity.ok(otpSentMessage);
    }
    public ResponseEntity<String> resetPassword(String email, String otp, String newPassword) {
        // Check if the email exists in the database
        User user = userRepo.findByUserName(email);
        if (user == null) {
            String emailNotFoundMessage = EMAIL_NOT_FOUND_MESSAGE;
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(emailNotFoundMessage);
        }

        // Check if the provided OTP matches the stored OTP
        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"subject\": \"" + "Invalid OTP" + "\"}");
        }

        // Check if the OTP has expired
        // Check if the OTP expiry time is null




        // Hash the new password
        String hashedPassword = passwordEncoder.encode(newPassword);

        // Update the user's password with the hashed password
        user.setUserPassword(hashedPassword);
        user.setOtp(null);

        userRepo.save(user);

        return ResponseEntity.ok("{\"subject\": \"" + "Password reset successful" + "\"}");
    }
    private Random random = new Random();


    private String generateOTP() {
        // Generate a random 6-digit OTP

        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendPasswordResetOTP(String email, String otp) {
        // Create a MailStructure object with the necessary email details
        MailStructure mailStructure = new MailStructure();
        mailStructure.setMail(email);
        mailStructure.setSubject("Password Reset OTP");
        mailStructure.setMessage("Dear User,\n\n"
                + "You have requested to reset your password. Your OTP (One Time Password) is: " + otp + "\n\n"
                + "Please use this OTP to reset your password.\n\n"
                + "If you did not request this, please ignore this email.\n\n"
                + "Best regards,\n"
                + "Your Company Name");

        // Send the email using the MailService
        mailService.sendMail(mailStructure);
    }


    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        String username = requestMap.get("username");
        String currentPassword = requestMap.get("currentPassword");
        String newPassword = requestMap.get("newPassword");
        // Assuming UserRepository has a method to fetch user by username
        User user = userRepo.findByUserName(username);

        if (user != null && passwordEncoder.matches(currentPassword, user.getUserPassword())) {
            // Hash the new password before saving
            String hashedPassword = passwordEncoder.encode(newPassword);

            // Update user's password
            user.setUserPassword(hashedPassword);
            userRepo.save(user);
            return ResponseEntity.ok("{\"subject\": \"Password changed successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"subject\": \"Incorrect username or password\"}");
        }
    }


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public List<User> assigneeByEmployee(String assigneeByEmployee){
        return userRepo.findByAssignee(assigneeByEmployee);
    }
    public List<User> assigneeScondaryByEmployee(String assigneeByEmployee){
        List<User> employeePrimaryList= userRepo.findByAssignee(assigneeByEmployee);
        List<User> employeeScondaryList= userRepo.findBySecondaryAssignee(assigneeByEmployee);
        List<User> finalEmployeeList= new ArrayList<>();
        finalEmployeeList.addAll(employeePrimaryList);
        finalEmployeeList.addAll(employeeScondaryList);
        return  finalEmployeeList;
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public User updateUserAssignee(String userName,String managerUserName, String secondaryManagerUserName){
        User user= userRepo.findByUserName(userName);
        User managerUser=userRepo.findByUserName(managerUserName);
        User managerUser1=userRepo.findByUserName(secondaryManagerUserName);
        if(user.getUserName().isBlank()){
            throw new EntityNotFoundException("user not found");
        }
        if(managerUser.getUserName().isBlank()){
            throw new EntityNotFoundException("manager not found");
        }
        if(managerUser1.getUserName().isBlank()){
            throw new EntityNotFoundException("Secondarymanager not found");
        }
        Role managerRole=roleRepo.findByRoleName(managerUser.getUserRole());
        Role managerRole1=roleRepo.findByRoleName(managerUser1.getUserRole());

        if(!managerRole.getRoleName().equals("MANAGER")|| !managerRole1.getRoleName().equals("MANAGER")){
            throw new EntityNotFoundException("Given user was not a manager");
        }
        user.setAssignee(managerUser.getUserName());
        user.setSecondaryAssignee(managerUser1.getUserName());
        return userRepo.save(user);
    }




}