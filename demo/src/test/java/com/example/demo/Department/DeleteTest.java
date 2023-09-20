package com.example.demo.Department;

import com.example.demo.controller.DepartmentController;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DeleteTest {

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

    @Test
    public void testDeleteDepartment() throws Exception {
        when(departmentService.delete(anyLong())).thenReturn(new DepartmentEntity());
        mockMvc.perform(MockMvcRequestBuilders.delete("/department/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteDepartmentError() throws Exception {
        when(departmentService.delete(anyLong())).thenThrow(new RuntimeException());
        mockMvc.perform(MockMvcRequestBuilders.delete("/department/delete/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error deleting the department, Enter Correct ID"));
    }
}
