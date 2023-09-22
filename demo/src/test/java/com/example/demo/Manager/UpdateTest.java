package com.example.demo.Manager;

import com.example.demo.controller.ManagerController;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.ManagerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UpdateTest {
    private MockMvc mockMvc;
    @Mock
    private ManagerService managerService;
    @InjectMocks
    private ManagerController managerController;

    public UpdateTest() throws JsonProcessingException {
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(managerController).build();
    }
    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    ManagerEntity Manager1=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    ObjectMapper objectMapper = new ObjectMapper();
    String departmentJson = objectMapper.writeValueAsString(Department1);
    String managerJson = objectMapper.writeValueAsString(Manager1);

    @Test
    public void Update_Manager_Test()throws Exception {
        when(managerService.update(any(ManagerEntity.class))).thenReturn(Manager1);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/manager/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(managerJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("arbaaz"));
    }

    @Test
    public void update_Manager_Test_Error()throws Exception {
        when(managerService.update(any(ManagerEntity.class))).thenThrow(new RuntimeException("Error updating"));
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/manager/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(managerJson))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error updating"));
    }
}
