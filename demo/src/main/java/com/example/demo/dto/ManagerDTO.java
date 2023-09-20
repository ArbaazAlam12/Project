package com.example.demo.dto;

import com.example.demo.entities.DepartmentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDTO {
    private long id;
    private String name;
    private String email;
    private String Contact;
    private DepartmentEntity department_id;
    private String status;


    public ManagerDTO(long id, String name, String email, String contact, DepartmentEntity department_id, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.Contact = contact;
        this.department_id = department_id;
        this.status=status;
    }

    public ManagerDTO() {

    }
}
