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
     * It helps to save the manager
     * @param managerEntity
     * @return manager from manager table
     */
    public ManagerEntity save(ManagerEntity managerEntity){
        return managerRepository.save(managerEntity);
    }

    /**
     * When delete manager is set to be not active, data is not deleted
     * @param id manager
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
     * It helps to update the manager entity
     * @param managerEntity
     * @return manager updated
     */
    public ManagerEntity update(ManagerEntity managerEntity){
        return managerRepository.save(managerEntity);
    }

    /**
     * This method helps to get all the managers but only those who are not deleted
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
     * It helps to find the find manager by id, customer mapper is used to convert entity to dto
     * and gets the manager
     * @param id
     * @return manager data
     */
    public ManagerDTO findById(Long id){
        ManagerDTO managerDTO = CustomerMapperManager.convertToManagerDTO(managerRepository.findById(id).get());
        return managerDTO;
    }

    /**
     * It helps to show all manager and join table based on primary key from department to
     *  foreign key from manager
     * @return list of manager
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
     * It helps to show manager by department id which is foreign key manager table
     * and join table based on primary key from department to
     *  foreign key from manager
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
     * It helps to change status of manager to active
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
}
