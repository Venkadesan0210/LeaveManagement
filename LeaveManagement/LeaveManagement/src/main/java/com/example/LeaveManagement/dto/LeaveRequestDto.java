package com.example.leavemanagement.dto;
public class LeaveRequestDto {
    private String userName;
    private String leaveType;
    private String fromDate;
    private String toDate;
    private String noOfDays;
    private String description;

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public String getNoOfDays() {
        return noOfDays;
    }
    public void setNoOfDays(String noOfDays) {
        this.noOfDays = noOfDays;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
