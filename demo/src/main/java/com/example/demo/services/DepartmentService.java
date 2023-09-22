package com.example.demo.services;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.utility.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * This is constructor which accepts department repository which inheritance the
     * JPA repository which helps to do crud operation without writing logic
     * @param departmentRepository
     */
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }

    /**
     * This method helps to save department
     * @param departmentEntity
     * @return saved department
     */
    public DepartmentEntity save(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

    /**
     * This method helps to get all the departments
     * @return list of departments
     */
    public List<DepartmentEntity> findAll(){
       return departmentRepository.findAll();
    }

    /**
     * It helps to delete the department by id of department
     * @param id
     */
    public DepartmentEntity delete(Long id){

        departmentRepository.deleteById(id);
        return null;
    }

    /**
     * It helps to find the find department by id, customer mapper is used to convert entity to dto
     * and gets the department
     * @param id
     * @return department Dto
     */
    public DepartmentDTO findById(Long id){
        DepartmentDTO departmentDTO= CustomMapper.convertToDepartmentDTO(departmentRepository.findById(id).get());
        return departmentDTO;
    }

    /**
     * It helps to update the department entity
     * @param departmentEntity
     * @return department enity
     */
   public DepartmentEntity update(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
   }
}
