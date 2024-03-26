package com.example.leavemanagement.service;
import com.example.leavemanagement.repo.LeaveRequestRepo;
import com.example.leavemanagement.repo.RoleRepo;
import com.example.leavemanagement.repo.UserRepo;
import com.example.leavemanagement.dto.UpdateLeaveRequestDto;
import com.example.leavemanagement.dto.LeaveRequestDto;
import com.example.leavemanagement.entity.*;
import com.example.leavemanagement.service.interfaces.LeaveRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LeaveRequestService implements LeaveRequestServiceImpl{
    private final LeaveRequestRepo leaveRequestRepo;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @Autowired
    public LeaveRequestService(LeaveRequestRepo leaveRequestRepo, UserRepo userRepo, RoleRepo roleRepo) {
        this.leaveRequestRepo = leaveRequestRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }
    public List<LeaveRequests> getAllLeaveRequest(){
        return leaveRequestRepo.findAll();
    }

    public LeaveRequests applyLeaveRequest(LeaveRequestDto leaveRequestDto){
        User user=userRepo.findByUserName(leaveRequestDto.getUserName());
        if(user.getUserName().isBlank()){
            throw new EntityNotFoundException("user not found");
        }
        if(user.getAssignee().isBlank()&& user.getAssignee().isEmpty()){
            throw new EntityNotFoundException("manager not found");
        }
        LeaveRequests leaveRequest=new LeaveRequests();
        leaveRequest.setLeaveType(leaveRequestDto.getLeaveType());
        leaveRequest.setDescription(leaveRequestDto.getDescription());
        leaveRequest.setFromDate(leaveRequestDto.getFromDate());
        leaveRequest.setToDate(leaveRequestDto.getToDate());
        leaveRequest.setNoOfDays(leaveRequestDto.getNoOfDays());
        leaveRequest.setEmployeeLeave(user.getEmployeeLeave());
        leaveRequest.setStatus(LeaveStatus.REQUESTED);
        return leaveRequestRepo.save(leaveRequest);
    }

    public ResponseEntity<LeaveRequests> updateLeaveRequest(UpdateLeaveRequestDto updateLeaveRequestDto){
        Optional<LeaveRequests> leaveRequests=leaveRequestRepo.findById(updateLeaveRequestDto.getLeaveRequestId());
        User user=userRepo.findByUserName(updateLeaveRequestDto.getUserName());
        Role userRole=roleRepo.findByRoleName(user.getUserRole());
        if(leaveRequests.isPresent() && (leaveRequests.get().getStatus().equals(LeaveStatus.REQUESTED)) && updateLeaveRequestDto.getApprovalStatus().equals(LeaveStatus.APPROVED) && userRole.getRoleName().equals("EMPLOYEE")){
            leaveRequests.get().setStatus(updateLeaveRequestDto.getApprovalStatus());
            Integer leaveCount = Integer.valueOf(leaveRequests.get().getNoOfDays());
            switch (leaveRequests.get().getLeaveType()){
                case ("marriageLeave"):
                    leaveRequests.get().getEmployeeLeave().setMarriageLeave(leaveRequests.get().getEmployeeLeave().getMarriageLeave()-leaveCount);
                    break;
                case ("privilegeLeave"):
                    leaveRequests.get().getEmployeeLeave().setPrivilegeLeave(leaveRequests.get().getEmployeeLeave().getPrivilegeLeave()-leaveCount);
                    break;
                case ("medicalLeave"):
                    leaveRequests.get().getEmployeeLeave().setMedicalLeave(leaveRequests.get().getEmployeeLeave().getMedicalLeave()-leaveCount);
                    break;
                case ("paternityLeave"):
                    leaveRequests.get().getEmployeeLeave().setPaternityLeave(leaveRequests.get().getEmployeeLeave().getPaternityLeave()-leaveCount);
                    break;
                default:
                    leaveRequests.get().getEmployeeLeave().setPrivilegeLeave(leaveRequests.get().getEmployeeLeave().getPrivilegeLeave()-leaveCount);
                    break;
            }
            leaveRequests.get().setUpdatedBy(user.getAssignee());
            LeaveRequests updatedLeaveRequests=leaveRequestRepo.save(leaveRequests.get());
            return ResponseEntity.ok().body(updatedLeaveRequests);
        } else if (leaveRequests.isPresent() && updateLeaveRequestDto.getApprovalStatus().equals(LeaveStatus.REJECTED)) {
            leaveRequests.get().setStatus(updateLeaveRequestDto.getApprovalStatus());
            LeaveRequests updatedLeaveRequests=leaveRequestRepo.save(leaveRequests.get());
            leaveRequests.get().setUpdatedBy(user.getAssignee());
            return ResponseEntity.ok().body(updatedLeaveRequests);
        }
        return ResponseEntity.unprocessableEntity().build();
    }
    public List<LeaveRequests> managerBasedRequests(String managerUserName){
        List<User> user=userRepo.findAll();
        List<LeaveRequests> leaveRequests=leaveRequestRepo.findAll();
        List<LeaveRequests> outputLeaveRequests=new ArrayList<>();
        List<User> filteredUsersByAssignee = new ArrayList<>();
        user.stream().filter(user1 ->
                user1.getAssignee()!=null
        ).toList().forEach(user1 -> {
            if(user1.getAssignee().equals(managerUserName)){
                filteredUsersByAssignee.add(user1);
            }
        });
        filteredUsersByAssignee.forEach(user1 -> leaveRequests.forEach(leaveRequests1 -> {
            if(user1.getEmployeeLeave().getId().equals(leaveRequests1.getEmployeeLeave().getId())){
                outputLeaveRequests.add(leaveRequests1);}
        }));
        return outputLeaveRequests;
    }
}
