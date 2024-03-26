package com.example.leavemanagement.controller;
import com.example.leavemanagement.dto.RoleDto;
import com.example.leavemanagement.entity.Role;
import com.example.leavemanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
