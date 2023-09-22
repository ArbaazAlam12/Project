package com.example.demo.controller;

import com.example.demo.Util.ApiConstant;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ApiConstant.EMPLOYEE_BASE)
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     *This post method helps to save the employee to employee table giving unique id
     * It returns the save employee from employee table
     * @param employeeEntity
     * @return
     */
    @PostMapping(ApiConstant.CREATE_EMPLOYEE)
    public ResponseEntity save(@RequestBody EmployeeEntity employeeEntity){
        try {
            return new ResponseEntity(employeeService.save(employeeEntity), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error saving the data",HttpStatus.OK);
        }
    }

    /**
     * This get method helps to get all the employee from employee table
     * It returns the all employees from employee table
     * @return
     */
    @GetMapping(ApiConstant.GET_ALL_EMPLOYEES)
    public ResponseEntity findAll(){
        try {
            return new ResponseEntity(employeeService.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error fetching all the data",HttpStatus.OK);
        }

    }

    /**
     * This method helps to get employee by employee id
     * @param id
     * @return
     */
    @GetMapping(ApiConstant.GET_EMPLOYEE_BY_ID)
    public ResponseEntity findById(@PathVariable long id){
        try {
            return new ResponseEntity(employeeService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Unable to find id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *This method update the employee provided by the api, updates based on body id of json
     * It returns the update employee
     * @param employeeEntity
     * @return
     */
    @PutMapping(ApiConstant.UPDATE_EMPLOYEE)
        public ResponseEntity update(@RequestBody EmployeeEntity employeeEntity){
            try {
                return new ResponseEntity(employeeService.update(employeeEntity), HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity("Unable to update", HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }

    /**
     *This delete method helps to delete the employee from employee table
     * It doesn't delete the data but sets it to not Active
     * @param id
     * @return
     */
    @DeleteMapping(ApiConstant.DELETE_EMPLOYEES)
        public ResponseEntity delete(@PathVariable Long id){
        try {
            return new ResponseEntity(employeeService.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Incorrect Id", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *This get method retrieve employee by the id given as parameter
     * @param managerId
     * @return
     */
    @GetMapping(ApiConstant.GET_EMPLOYEES_By_MANAGER_ID)
    public ResponseEntity<List<EmployeeEntity>> getEmployeeByManagerId(@PathVariable long managerId){
        try {
            return new ResponseEntity<> (employeeService.findEmployeeByManagerId(managerId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error assigning the Id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method assign list of employee to new manager
     * @param managerId
     * @param requestBody
     * @return
     */
    @PostMapping(ApiConstant.ASSIGN_EMPLOYEES_TO_MANAGER)
    public ResponseEntity assignEmployeesToManager(
            @PathVariable("managerId") Long managerId,
            @RequestBody Map<String, List<Long>> requestBody) {
        try {
            List<Long> employeeId = requestBody.get("employeeId");
            return new ResponseEntity(employeeService.assignEmployeeToManagerId(employeeId, managerId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error assigning the Id", HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    /**
     * This method changes the status of manager from not active to active
     * @param id
     * @return
     */
    @PostMapping(ApiConstant.CHANGE_EMPLOYEE_STATUS_TO_ACTIVE)
    public ResponseEntity changeStatusActive(@PathVariable long id){
        try {
            return new ResponseEntity(employeeService.changeStatusActive(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Unable to change status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
