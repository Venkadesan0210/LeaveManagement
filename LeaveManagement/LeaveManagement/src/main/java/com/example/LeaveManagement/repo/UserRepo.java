package com.example.LeaveManagement.repo;
import com.example.LeaveManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<User, String> {
    List<User> findAll();
    User findByUserName(String userName);

}
