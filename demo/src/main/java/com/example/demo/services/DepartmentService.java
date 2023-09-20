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
     *
     * @param departmentRepository
     */
    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository=departmentRepository;
    }

    /**
     *
     * @param departmentEntity
     * @return
     */
    public DepartmentEntity save(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
    }

    /**
     *
     * @return
     */
    public List<DepartmentEntity> findAll(){
       return departmentRepository.findAll();
    }

    /**
     *
     * @param id
     */
    public DepartmentEntity delete(Long id){

        departmentRepository.deleteById(id);
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public DepartmentDTO findById(Long id){
        DepartmentDTO departmentDTO= CustomMapper.convertToDepartmentDTO(departmentRepository.findById(id).get());
        return departmentDTO;
    }

    /**
     *
     * @param departmentEntity
     * @return
     */
   public DepartmentEntity update(DepartmentEntity departmentEntity){
        return departmentRepository.save(departmentEntity);
   }
}
