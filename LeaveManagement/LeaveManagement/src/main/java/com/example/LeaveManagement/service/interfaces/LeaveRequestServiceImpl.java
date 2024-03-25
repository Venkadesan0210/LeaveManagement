package com.example.LeaveManagement.service.interfaces;
import com.example.LeaveManagement.dto.UpdateLeaveRequestDto;
import com.example.LeaveManagement.dto.LeaveRequestDto;
import com.example.LeaveManagement.entity.LeaveRequests;
import org.springframework.http.ResponseEntity;
import java.util.List;
public interface LeaveRequestServiceImpl {
    List<LeaveRequests> getAllLeaveRequest();
    LeaveRequests applyLeaveRequest(LeaveRequestDto leaveRequestDto);
    ResponseEntity<LeaveRequests> updateLeaveRequest(UpdateLeaveRequestDto updateLeaveRequestDto);
}
