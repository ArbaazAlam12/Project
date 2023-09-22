package com.example.demo.controller;

import com.example.demo.Util.ApiConstant;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.DEPARTMENT_BASE)
public class DepartmentController {


    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * This get method helps to get all the department from department table
     * It returns the all departments from department table
     * @return
     */
    @GetMapping(ApiConstant.GET_ALL_DEPARTMENT)
    public ResponseEntity findAllDepartment() {
        try{
            return new ResponseEntity(departmentService.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity("Error finding the data", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *This post method helps to save the department to department table giving uniqye id
     * It returns the save department from department table
     * @param departmentEntity
     * @return
     */
    @PostMapping(ApiConstant.CREATE_DEPARTMENT)
    public ResponseEntity save(@RequestBody DepartmentEntity departmentEntity) {
        try {
            return new ResponseEntity(departmentService.save(departmentEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error saving the data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *This delete method helps to delete the department from department table, and it takes id as parameter
     * @param id
     * @return
     */
    @DeleteMapping(ApiConstant.DELETE_DEPARTMENT)
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return new ResponseEntity(departmentService.delete(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error deleting the department, Enter Correct ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *This get method retrieve department by the id given as parameter
     * @param id
     * @return
     */
    @GetMapping(ApiConstant.GET_DEPARTMENT_BY_ID)
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            return new ResponseEntity(departmentService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error finding the Id, Enter Correct ID", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *This method update the department provided by the api, updates based on body id of json
     * It returns the update department
     * @param departmentEntity
     * @return
     */
    @PutMapping(ApiConstant.UPDATE_DEPARTMENT)
    public ResponseEntity update(@RequestBody DepartmentEntity departmentEntity) {
        try {
            return new ResponseEntity(departmentService.update(departmentEntity), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity("Error Updating the department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
