package com.example.leavemanagement.repo;
import com.example.leavemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRepo extends JpaRepository<User, String> {
    List<User> findAll();
    User findByUserName(String userName);
    List<User> findByAssignee(String assignee);
}
