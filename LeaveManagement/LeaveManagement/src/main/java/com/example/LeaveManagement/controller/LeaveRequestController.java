package com.example.leavemanagement.controller;
import com.example.leavemanagement.dto.LeaveRequestByManagerDto;
import com.example.leavemanagement.dto.LeaveRequestDto;
import com.example.leavemanagement.dto.LeaveRequestOutputDto;
import com.example.leavemanagement.dto.UpdateLeaveRequestDto;
import com.example.leavemanagement.entity.LeaveRequests;
import com.example.leavemanagement.service.LeaveRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }
    @GetMapping("/leaves")
    public List<LeaveRequests> getAllLeaves(){
        return leaveRequestService.getAllLeaveRequest();
    }
    @GetMapping("/userLeaves/{employeeId}")
    public List<LeaveRequests> getLeavesById(@PathVariable Long employeeId){
        return leaveRequestService.getLeaveRequestById(employeeId);
    }
    @PostMapping("/apply-leave")
    public LeaveRequests applyForLeave(@RequestBody LeaveRequestDto leaveRequestDto){
        return leaveRequestService.applyLeaveRequest(leaveRequestDto);
    }
    @PostMapping("/update-leave")
    public ResponseEntity<LeaveRequests> updatedLeaveRequest(@RequestBody UpdateLeaveRequestDto updateLeaveRequestDto){
        return leaveRequestService.updateLeaveRequest(updateLeaveRequestDto);
    }
    @PostMapping("/leaves-manager")
    public List<LeaveRequestOutputDto> getLeaveRequestByManagerName(@RequestBody  LeaveRequestByManagerDto leaveRequestByManagerDto){
        return leaveRequestService.managerBasedRequests(leaveRequestByManagerDto.getManagerName());
    }

}
