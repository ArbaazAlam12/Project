package com.example.demo.services;

import com.example.demo.Util.VariableConstant;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.*;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.utility.CustomerMapperEmployee;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public EmployeeService(EmployeeRepository employeeRepository, EntityManager entityManager) {
        this.employeeRepository = employeeRepository;
        this.entityManager=entityManager;
    }

    /**
     * This method helps to save department
     * @param employeeEntity
     * @return
     */
    public EmployeeEntity save(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
    }

    /**
     * When delete employee is set to be not active, data is not deleted
     * @param id
     */
    public EmployeeEntity delete(Long id){
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            employee.setStatus(VariableConstant.Not_Active);
            employeeRepository.save(employee);
            return employee;
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " not found");
        }
    }

    /**
     * This method helps to get all the employees
     * @return
     */
    public List<EmployeeEntity> findAll(){
        JPAQuery<EmployeeEntity> query = new JPAQuery<>(entityManager);
        QEmployeeEntity employee = QEmployeeEntity.employeeEntity;
        return query.select(employee)
                .from(employee)
                .where(employee.status.eq("A"))
                .fetch();
    }

    /**
     * It helps to find the find employee by id, customer mapper is used to convert entity to dto
     * and gets the employee
     * @param id
     * @return
     */
    public EmployeeDTO findById(Long id){
        EmployeeDTO employeeDTO= CustomerMapperEmployee.convertToManagerDto(employeeRepository.findById(id).get());
        return employeeDTO;
   }

    /**
     * It helps to update the employee entity
     * @param employeeEntity
     * @return
     */
   public EmployeeEntity update(EmployeeEntity employeeEntity){
        return employeeRepository.save(employeeEntity);
   }

    /**
     * It helps to show employee by manager id which is foreign key employee table
     * and join table based on primary key from manager to
     *  foreign key from employee
     * @param managerId
     * @return
     */
    public List<EmployeeEntity> findEmployeeByManagerId(Long managerId) {
        JPAQuery<EmployeeEntity> query = new JPAQuery<>(entityManager);
        QManagerEntity qManagerEntity = QManagerEntity.managerEntity;
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        return query.select(qEmployeeEntity)
                .from(qEmployeeEntity)
                .innerJoin(qEmployeeEntity.manager, qManagerEntity)
                .where(qManagerEntity.id.eq(managerId))
                .fetch();
    }

    /**
     * It takes parameter of employee ids and manager id and changes employee ids
     * to manager id and return the data, transaction is used, so it can be rollback
     * @param employeeId
     * @param managerId
     * @return employee with new manager id
     */
    @Transactional
    public List<EmployeeEntity> assignEmployeeToManagerId(List<Long> employeeId, Long managerId) {
        QEmployeeEntity qEmployeeEntity = QEmployeeEntity.employeeEntity;
        new JPAUpdateClause(entityManager, qEmployeeEntity)
                .set(qEmployeeEntity.manager.id, managerId)
                .where(qEmployeeEntity.id.in(employeeId))
                .execute();

        return new JPAQuery<>(entityManager)
                .select(qEmployeeEntity)
                .from(qEmployeeEntity)
                .where(qEmployeeEntity.id.in(employeeId))
                .fetch();
    }

    /**
     * It helps to changes the status of employee to active which was deleted
     * @param id
     */
    public EmployeeEntity changeStatusActive(Long id){
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            employee.setStatus(VariableConstant.Active);
            employeeRepository.save(employee);
            return employee;
        } else {
            throw new IllegalArgumentException("Employee with ID " + id + " not found");
        }
    }
}
