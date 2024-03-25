package com.example.LeaveManagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.LeaveManagement.dto.RoleDto;
import com.example.LeaveManagement.repo.RoleRepo;
import com.example.LeaveManagement.entity.Role;

public class RoleService {
    private final RoleRepo roleRepo;
    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role createNewRole(RoleDto roleDto) {

        Role role=new Role();
        role.setRoleName(roleDto.getRoleName());
        role.setRoleDescription(roleDto.getRoleDescription());
        return roleRepo.save(role);
    }
}