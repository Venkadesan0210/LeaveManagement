package com.example.leavemanagement.dto;
import com.example.leavemanagement.entity.EmployeeLeave;
import com.example.leavemanagement.entity.LeaveStatus;
public class LeaveRequestOutputDto {
    private String userName;
    private String leaveType;
    private String fromDate;
    private String toDate;
    private String noOfDays;
    private String description;
    private EmployeeLeave employeeLeave;
    private LeaveStatus status;
    private Long leaveRequestId;
    private String managerDescription;

    public String getManagerDescription() {
        return managerDescription;
    }

    public void setManagerDescription(String managerDescription) {
        this.managerDescription = managerDescription;
    }

    public Long getLeaveRequestId() {
        return leaveRequestId;
    }
    public void setLeaveRequestId(Long leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }
    public EmployeeLeave getEmployeeLeave() {
        return employeeLeave;
    }
    public void setEmployeeLeave(EmployeeLeave employeeLeave) {
        this.employeeLeave = employeeLeave;
    }
    public LeaveStatus getStatus() {
        return status;
    }
    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
    private String updatedBy;
    public String getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
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
