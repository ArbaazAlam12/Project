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
     *
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
     *
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
     *
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
     *
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
     *
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
