package com.example.leavemanagement.dto;
import com.example.leavemanagement.entity.LeaveStatus;
public class UpdateLeaveRequestDto {
    private String userName;
    private Long leaveRequestId;
    private LeaveStatus approvalStatus;
    public LeaveStatus getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(LeaveStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getLeaveRequestId() {
        return leaveRequestId;
    }
    public void setLeaveRequestId(Long leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }
}
