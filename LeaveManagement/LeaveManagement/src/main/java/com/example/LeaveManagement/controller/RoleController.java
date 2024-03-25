package com.example.LeaveManagement.controller;

import com.example.LeaveManagement.dto.RoleDto;
import com.example.LeaveManagement.entity.Role;
import com.example.LeaveManagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody RoleDto roleDto) {

        return roleService.createNewRole(roleDto);
    }

}
