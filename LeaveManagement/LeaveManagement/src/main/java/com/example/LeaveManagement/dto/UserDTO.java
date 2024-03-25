package com.example.LeaveManagement.dto;

public class UserDTO {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;

    private String userRole;

    public String getUserName() {
        return userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName=userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName=userLastName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword=userPassword;
    }

    public void setUserRole(String userRole) {
        this.userRole=userRole;
    }
}
