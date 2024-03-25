package com.example.LeaveManagement.service;
import com.example.LeaveManagement.repo.RoleRepo;
import com.example.LeaveManagement.repo.UserRepo;
import com.example.LeaveManagement.dto.UserDTO;
import com.example.LeaveManagement.entity.EmployeeLeave;
import com.example.LeaveManagement.entity.User;
import com.example.LeaveManagement.entity.Role;
import com.example.LeaveManagement.service.interfaces.UserServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserServiceInt {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
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

    public User registerNewUser(UserDTO userDTO) {
        User user = new User();
        Role role = roleRepo.findByRoleName(userDTO.getUserRole());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setUserName(userDTO.getUserName());
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(userDTO.getUserPassword()));
        user.setUserRole(role.getRoleName());
        user.setUserFirstName(userDTO.getUserFirstName());
        user.setUserLastName(userDTO.getUserLastName());
        if(role.getRoleName().equals("EMPLOYEE")){
            EmployeeLeave employeeLeave=new EmployeeLeave();
            employeeLeave.setMarriageLeave(3);
            employeeLeave.setMedicalLeave(10);
            employeeLeave.setPrivilegeLeave(10);
            employeeLeave.setPaternityLeave(5);
            user.setEmployeeLeave(employeeLeave);
        }
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
    public User updateUserAssignee(String userName,String managerUserName){
        User user= userRepo.findByUserName(userName);
        User managerUser=userRepo.findByUserName(managerUserName);

        if(user.getUserName().isBlank()){
            throw new EntityNotFoundException("user not found");
        }
        if(managerUser.getUserName().isBlank()){
            throw new EntityNotFoundException("manager not found");
        }
        Role managerRole=roleRepo.findByRoleName(managerUser.getUserRole());

        if(!managerRole.getRoleName().equals("MANAGER")){
            throw new EntityNotFoundException("Given user was not a manager");
        }
        user.setAssignee(managerUser.getUserName());
        return userRepo.save(user);
    }
}