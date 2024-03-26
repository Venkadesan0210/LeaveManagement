package com.example.leavemanagement.repo;
import com.example.leavemanagement.entity.LeaveRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequests, Long> {
}
