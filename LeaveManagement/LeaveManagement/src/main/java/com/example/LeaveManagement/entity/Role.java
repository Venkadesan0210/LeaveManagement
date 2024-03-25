package com.example.LeaveManagement.entity;

import javax.persistence.Id;

public class Role {

    @Id
    private String roleName;
    private String roleDescription;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {

        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {

        this.roleDescription = roleDescription;
    }

}
