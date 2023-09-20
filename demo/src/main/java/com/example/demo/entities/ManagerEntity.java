package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.naming.factory.SendMailFactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name="Manager")
public class ManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(columnDefinition = "varchar(255) default='A'")
    private String status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department_id;

    public ManagerEntity(Long id, String name, String email, String contact, String status, DepartmentEntity department_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.status = status;
        this.department_id = department_id;
    }


    public ManagerEntity() {

    }
}

