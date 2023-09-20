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

public class UpdateTest {
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        DepartmentEntity updatedDepartment = new DepartmentEntity();
        updatedDepartment.setId(1L);
        updatedDepartment.setName("HR Department");
        when(departmentService.update(any(DepartmentEntity.class))).thenReturn(updatedDepartment);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/department/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR Department\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("HR Department"));
    }

    @Test
    public void testUpdateDepartmentError() throws Exception {
        DepartmentEntity updatedDepartment = new DepartmentEntity();
        updatedDepartment.setId(1L);
        updatedDepartment.setName("HR Department");
        when(departmentService.update(any(DepartmentEntity.class))).thenThrow(new RuntimeException("Error Updating the department"));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/department/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"HR Department\"}"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error Updating the department"));
    }
}
