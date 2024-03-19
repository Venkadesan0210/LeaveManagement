package com.example.LeaveManagement.users.UserEntity;


import jakarta.persistence.*;

import javax.management.relation.Role;

@Entity
@Table(name="User")
public class User {


    @Id
    @Column(name="user_id",length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    @Column(name="user_name",length = 255)
    private String username;
    @Column(name="password",length=255)
    private String password;
    @Column(nullable = false)
    private String role;

    public User(int userid, String username, String password, String role) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }


}
