package com.example.demo.utility;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.DepartmentEntity;

public class CustomMapper {
    public static DepartmentDTO convertToDepartmentDTO(DepartmentEntity departmentEntity){
        return new DepartmentDTO(departmentEntity.getId(), departmentEntity.getName());
    }

}
