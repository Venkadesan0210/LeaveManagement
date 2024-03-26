package com.example.leavemanagement.entity;
import javax.persistence.*;
import java.util.Set;
@Entity
public class User {
    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userRole;
    private String assignee;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="emp_leave_id" ,referencedColumnName = "id")
    private EmployeeLeave employeeLeave;
    public EmployeeLeave getEmployeeLeave() {
        return employeeLeave;
    }
    public void setEmployeeLeave(EmployeeLeave employeeLeave) {
        this.employeeLeave = employeeLeave;
    }
    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public Role setRole(Set<Role> role) {
        this.role = role;
        return null;
    }
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
