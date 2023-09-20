package com.example.demo.repository;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.ManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface ManagerRepository extends JpaRepository<ManagerEntity, Long>, QuerydslPredicateExecutor<EmployeeEntity> {
}
