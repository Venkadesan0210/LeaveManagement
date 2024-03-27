package com.example.leavemanagement.repo;
import com.example.leavemanagement.entity.LeaveRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequests, Long> {
    List<LeaveRequests> findByEmployeeLeaveId(Long employeeLeaveId);
}
