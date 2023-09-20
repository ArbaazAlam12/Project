package com.example.demo.Department;

import com.example.demo.controller.DepartmentController;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetTest {
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    DepartmentEntity Department2 = new DepartmentEntity(2L, "Finance");

    @Before
    public void setUp() {
        departmentService = mock(DepartmentService.class);
        departmentController = new DepartmentController(departmentService);
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    public void Get_All_Department() throws  Exception{
        List<DepartmentEntity> departmentDTOList = new ArrayList<>(Arrays.asList(Department1, Department2));
        when(departmentService.findAll()).thenReturn(departmentDTOList);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/department/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))
        );
    }

    @Test
    public void Get_By_Department_Id() throws  Exception{
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(1L);
        departmentDTO.setName("Test Department");
        when(departmentService.findById(1L)).thenReturn(departmentDTO);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department/findById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Department")
                );
    }
}
