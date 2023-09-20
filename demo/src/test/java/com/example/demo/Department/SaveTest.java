package com.example.demo.Department;

import com.example.demo.controller.DepartmentController;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;

public class SaveTest {
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }
    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");

    @Test
    public void testsaveDepartment() throws Exception {
        DepartmentEntity updatedDepartment = new DepartmentEntity();
        updatedDepartment.setId(1L);
        updatedDepartment.setName("HR Department");
        when(departmentService.save(any(DepartmentEntity.class))).thenReturn(updatedDepartment);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/department/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR Department\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("HR Department"));
    }

    @Test
    public void testsaveDepartmentError() throws Exception {
        DepartmentEntity updatedDepartment = new DepartmentEntity();
        updatedDepartment.setId(1L);
        updatedDepartment.setName("HR Department");
        when(departmentService.save(any(DepartmentEntity.class))).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/department/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR Department\"}"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
