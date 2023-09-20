package com.example.demo.controller;

import com.example.demo.Util.ApiConstant;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.MANAGER_BASE)
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    /**
     *
     * @return
     */
    @GetMapping(ApiConstant.GET_ALL_MANAGER)
    public ResponseEntity findAllManager(){
        return new ResponseEntity(managerService.FindAll(), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(ApiConstant.DELETE_MANAGER)
    public ResponseEntity deleteManager(@PathVariable Long id){
        try {
            return new ResponseEntity(managerService.delete(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Incorrect Id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *
     * @param managerEntity
     * @return
     */
    @PostMapping(ApiConstant.CREATE_MANAGER)
    public ResponseEntity saveManager(@RequestBody ManagerEntity managerEntity){
        return new ResponseEntity(managerService.save(managerEntity), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(ApiConstant.GET_MANAGER_BY_ID)
    public ResponseEntity findByIdManager(@PathVariable Long id){
        try {
            return new ResponseEntity(managerService.findById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Cannot find the id entered", HttpStatus.OK);
        }

    }

    /**
     *
     * @param managerEntity
     * @return
     */
    @PutMapping(ApiConstant.UPDATE_MANAGER)
    public ResponseEntity update(@RequestBody ManagerEntity managerEntity){
        try {
            return new ResponseEntity(managerService.update(managerEntity), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Error updating",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *
     * @return
     */
    @GetMapping(ApiConstant.MANAGER_IN_DEPARTMENT)
    public List<ManagerEntity> getManagersInDepartments() {
        return managerService.findManagersInDepartments();
    }

    /**
     *
     * @param departmentId
     * @return
     */
    @GetMapping(ApiConstant.MANAGER_IN_DEPARTMENT_BY_ID)
    public ResponseEntity<List<ManagerEntity>> getManagersInDepartmentById(@PathVariable Long departmentId) {
        try {
            return new ResponseEntity<>(managerService.findManagersInDepartmentById(departmentId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping(ApiConstant.CHANGE_MANAGER_STATUS_TO_ACTIVE)
    public ResponseEntity changeStatusActive(@PathVariable long id){
        try {
            return new ResponseEntity(managerService.changeStatusActive(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Unable to change status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
