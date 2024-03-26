package com.example.leavemanagement.entity;
import javax.persistence.*;
@Entity
@Table(name = "employee_leave")
public class EmployeeLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Integer marriageLeave;
    private  Integer privilegeLeave;
    private  Integer medicalLeave;
    private  Integer paternityLeave;
    public EmployeeLeave() {
    }

    public EmployeeLeave(Long id, Integer marriageLeave, Integer privilegeLeave, Integer medicalLeave, Integer paternityLeave) {
        this.id = id;
        this.marriageLeave = marriageLeave;
        this.privilegeLeave = privilegeLeave;
        this.medicalLeave = medicalLeave;
        this.paternityLeave = paternityLeave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMarriageLeave() {
        return marriageLeave;
    }

    public void setMarriageLeave(Integer marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    public Integer getPrivilegeLeave() {
        return privilegeLeave;
    }

    public void setPrivilegeLeave(Integer privilegeLeave) {
        this.privilegeLeave = privilegeLeave;
    }

    public Integer getMedicalLeave() {
        return medicalLeave;
    }

    public void setMedicalLeave(Integer medicalLeave) {
        this.medicalLeave = medicalLeave;
    }

    public Integer getPaternityLeave() {
        return paternityLeave;
    }

    public void setPaternityLeave(Integer paternityLeave) {
        this.paternityLeave = paternityLeave;
    }
}
