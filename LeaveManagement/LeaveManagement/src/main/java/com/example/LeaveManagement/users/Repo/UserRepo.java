package com.example.LeaveManagement.users.Repo;

import com.example.LeaveManagement.users.UserEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
