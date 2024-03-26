package com.example.leavemanagement.dto;
public class UpdateUserAssigneeDto {

    private String userName;
    private String managerUserName;
    public UpdateUserAssigneeDto() {
    }
    public String getUserName() {
        return userName;
    }
    public String getManagerUserName() {
        return managerUserName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setManagerUserName(String managerUserName) {
        this.managerUserName = managerUserName;
    }
    public UpdateUserAssigneeDto(String userName, String managerUserName) {
        this.userName = userName;
        this.managerUserName = managerUserName;
    }
}
