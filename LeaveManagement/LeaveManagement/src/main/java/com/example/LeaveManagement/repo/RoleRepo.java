package com.example.LeaveManagement.repo;
import com.example.LeaveManagement.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, String> {
    Role findByRoleName(String role);
}
