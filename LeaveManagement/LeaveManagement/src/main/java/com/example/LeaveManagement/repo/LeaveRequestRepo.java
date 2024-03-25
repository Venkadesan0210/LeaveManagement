package com.example.LeaveManagement.repo;

import com.example.LeaveManagement.entity.LeaveRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequests, Long> {
}
