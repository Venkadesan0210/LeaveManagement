package com.example.leavemanagement.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.leavemanagement.dto.RoleDto;
import com.example.leavemanagement.repo.RoleRepo;
import com.example.leavemanagement.entity.Role;
import org.springframework.stereotype.Service;
@Service
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