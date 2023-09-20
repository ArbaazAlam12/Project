package com.example.demo.utility;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.EmployeeEntity;

public class CustomerMapperEmployee {
    public static EmployeeDTO convertToManagerDto(EmployeeEntity employeeEntity){
        return new EmployeeDTO(employeeEntity.getId(),employeeEntity.getName(),employeeEntity.getEmail(), employeeEntity.getContact(),employeeEntity.getStatus(), employeeEntity.getDepartment(), employeeEntity.getManager());
    }
}
