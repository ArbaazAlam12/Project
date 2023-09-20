package com.example.demo.utility;

import com.example.demo.dto.ManagerDTO;
import com.example.demo.entities.ManagerEntity;

public class CustomerMapperManager {
    public static ManagerDTO convertToManagerDTO(ManagerEntity managerEntity){
        return new ManagerDTO(managerEntity.getId(), managerEntity.getName(), managerEntity.getEmail(), managerEntity.getContact(), managerEntity.getDepartment_id(), managerEntity.getStatus());
    }
}
