package com.example.demo.Manager;

import com.example.demo.controller.DepartmentController;
import com.example.demo.controller.ManagerController;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.ManagerDTO;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.ManagerService;
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
public class FindTest {
    private MockMvc mockMvc;

    @Mock
    private ManagerService managerService;

    @InjectMocks
    private ManagerController managerController;

    @Before
    public void setUp() {
        managerService = mock(ManagerService.class);
        managerController = new ManagerController(managerService);
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }
    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    ManagerEntity Manager1=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    ManagerEntity Manager2=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    ManagerDTO Manager3=new ManagerDTO(1L, "arbaaz", "1233", "12333", Department1, "A");

    @Test
    public void Get_All_Manager() throws  Exception{
        List<ManagerEntity> managerEntityList = new ArrayList<>(Arrays.asList(Manager1, Manager2));
        when(managerService.FindAll()).thenReturn(managerEntityList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/manager/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))
                );
    }
    @Test
    public void Get_By_Manager_Id() throws  Exception{
        when(managerService.findById(1L)).thenReturn(Manager3);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/manager/findById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("arbaaz")
                );
    }
}
