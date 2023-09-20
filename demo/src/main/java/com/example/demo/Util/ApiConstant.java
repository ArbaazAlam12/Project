package com.example.demo.Util;

public class ApiConstant {
    public static final String EMPLOYEE_BASE = "/employee";
    public static final String CREATE_EMPLOYEE = "/save";
    public static final String UPDATE_EMPLOYEE = "/update";
    public static final String GET_EMPLOYEE_BY_ID = "/findById/{id}";
    public static final String GET_ALL_EMPLOYEES = "/findAll";
    public static final String DELETE_EMPLOYEES = "/delete/{id}";
    public static final String GET_EMPLOYEES_By_MANAGER_ID = "/allEmployee/{managerId}";
    public static final String ASSIGN_EMPLOYEES_TO_MANAGER = "/assignToManager/{managerId}";
    public static final String CHANGE_EMPLOYEE_STATUS_TO_ACTIVE = "/changeStatus/{id}";

    public static final String DEPARTMENT_BASE = "/department";
    public static final String CREATE_DEPARTMENT = "/save";
    public static final String UPDATE_DEPARTMENT = "/update";
    public static final String GET_DEPARTMENT_BY_ID = "/findById/{id}";
    public static final String GET_ALL_DEPARTMENT = "/findAll";
    public static final String DELETE_DEPARTMENT = "/delete/{id}";

    public static final String MANAGER_BASE = "/manager";
    public static final String CREATE_MANAGER = "/save";
    public static final String UPDATE_MANAGER = "/update";
    public static final String GET_MANAGER_BY_ID = "/findById/{id}";
    public static final String GET_ALL_MANAGER = "/findAll";
    public static final String DELETE_MANAGER = "/delete/{id}";
    public static final String MANAGER_IN_DEPARTMENT = "/inDepartments";
    public static final String MANAGER_IN_DEPARTMENT_BY_ID = "/inDepartments/{departmentId}";
    public static final String CHANGE_MANAGER_STATUS_TO_ACTIVE = "/changeStatus/{id}";


}
