package com.example.demo.dto;


import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.ManagerEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String contact;
    private String status;
    private DepartmentEntity department;
    private ManagerEntity manager;

    public EmployeeDTO(Long id, String name, String email, String contact, String status, DepartmentEntity department, ManagerEntity manager) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.department = department;
        this.manager = manager;
        this.status=status;
    }
}
