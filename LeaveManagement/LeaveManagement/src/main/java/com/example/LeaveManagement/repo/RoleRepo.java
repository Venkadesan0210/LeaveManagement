package com.example.leavemanagement.repo;
import com.example.leavemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepo extends JpaRepository<Role, String> {
    Role findByRoleName(String role);
}
