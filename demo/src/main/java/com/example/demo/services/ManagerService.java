package com.example.demo.services;

import com.example.demo.Util.VariableConstant;
import com.example.demo.dto.ManagerDTO;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.entities.QDepartmentEntity;
import com.example.demo.entities.QManagerEntity;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.utility.CustomerMapperManager;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private ManagerRepository managerRepository;
    private EntityManager entityManager ;

    public ManagerService(ManagerRepository managerRepository, EntityManager entityManager){
        this.managerRepository=managerRepository;
        this.entityManager=entityManager;
    }

    /**
     *
     * @param managerEntity
     * @return
     */
    public ManagerEntity save(ManagerEntity managerEntity){
        return managerRepository.save(managerEntity);
    }

    /**
     *
     * @param id
     */
    public ManagerEntity delete(Long id){
        Optional<ManagerEntity> optionalManager = managerRepository.findById(id);
        if (optionalManager.isPresent()) {
            ManagerEntity manager = optionalManager.get();
            manager.setStatus(VariableConstant.Not_Active);
            managerRepository.save(manager);
            return manager;
        } else {
            throw new IllegalArgumentException("Manager with ID " + id + " not found");
        }
    }

    /**
     *
     * @param managerEntity
     * @return
     */
    public ManagerEntity update(ManagerEntity managerEntity){
        return managerRepository.save(managerEntity);
    }

    /**
     *
     * @return
     */
    public List<ManagerEntity> FindAll(){
        JPAQuery<ManagerEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity manager = QManagerEntity.managerEntity;
        return query.select(manager)
                .from(manager)
                .where(manager.status.eq("A"))
                .fetch();
    }

    /**
     *
     * @param id
     * @return
     */
    public ManagerDTO findById(Long id){
        ManagerDTO managerDTO = CustomerMapperManager.convertToManagerDTO(managerRepository.findById(id).get());
        return managerDTO;
    }

    /**
     *
     * @return
     */
    public List<ManagerEntity> findManagersInDepartments() {
        JPAQuery<ManagerEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity manager = QManagerEntity.managerEntity;
        QDepartmentEntity department = QDepartmentEntity.departmentEntity;

        return query.select(manager)
                .from(manager)
                .leftJoin(manager.department_id, department)
                .fetchJoin()
                .fetch();
    }

    /**
     *
     * @param departmentId
     * @return
     */
    public List<ManagerEntity> findManagersInDepartmentById(Long departmentId) {
        JPAQuery<ManagerEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity manager = QManagerEntity.managerEntity;
        QDepartmentEntity department = QDepartmentEntity.departmentEntity;

        return query.select(manager)
                .from(manager)
                .leftJoin(manager.department_id, department)
                .where(department.id.eq(departmentId))
                .fetchJoin()
                .fetch();
    }

    /**
     *
     * @param id
     */
    public ManagerEntity changeStatusActive(Long id){
        Optional<ManagerEntity> optionalManager = managerRepository.findById(id);
        if (optionalManager.isPresent()) {
            ManagerEntity manager = optionalManager.get();
            manager.setStatus(VariableConstant.Active);
            managerRepository.save(manager);
            return manager;
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " not found");
        }
    }

//    public ManagerEntity changeStatusActive(Long id){
//        Optional<ManagerEntity> optionalManager = managerRepository.findById(id);
//        if (optionalManager.isPresent()) {
//            ManagerEntity manager = optionalManager.get();
//            if (manager.getStatus()==VariableConstant.Active) {
//                manager.setStatus(VariableConstant.Not_Active);
//                managerRepository.save(manager);
//            }
//            else {
//                manager.setStatus(VariableConstant.Active);
//                managerRepository.save(manager);
//            }
//            return manager;
//        } else {
//            throw new IllegalArgumentException("Employee with ID " + id + " not found");
//        }
//    }
}
