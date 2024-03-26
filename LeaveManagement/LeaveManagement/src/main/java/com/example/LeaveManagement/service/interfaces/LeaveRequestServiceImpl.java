package com.example.leavemanagement.service.interfaces;
import com.example.leavemanagement.dto.UpdateLeaveRequestDto;
import com.example.leavemanagement.dto.LeaveRequestDto;
import com.example.leavemanagement.entity.LeaveRequests;
import org.springframework.http.ResponseEntity;
import java.util.List;
public interface LeaveRequestServiceImpl {
    List<LeaveRequests> getAllLeaveRequest();
    LeaveRequests applyLeaveRequest(LeaveRequestDto leaveRequestDto);
    ResponseEntity<LeaveRequests> updateLeaveRequest(UpdateLeaveRequestDto updateLeaveRequestDto);
}
