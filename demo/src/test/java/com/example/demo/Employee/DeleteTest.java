package com.example.demo.Employee;

import com.example.demo.controller.EmployeeController;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.EmployeeService;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTest {
    private MockMvc mockMvc;
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    ManagerEntity Manager1=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    EmployeeEntity Employee1=new EmployeeEntity(1L, "ram", "1233", "1233", "AAA", "A", Department1,Manager1);
    EmployeeEntity Employee2=new EmployeeEntity(1L, "ram", "1233", "1233", "AAA", "NA", Department1,Manager1);

    @Test
    public void Change_Status_To_NotActive()throws Exception{
        when(employeeService.delete(Employee1.getId())).thenReturn(Employee2);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NA"));
    }

    @Test
    public void Change_Status_To_NotActive_Error()throws Exception{
        when(employeeService.delete(Employee1.getId())).thenThrow(new RuntimeException("Incorrect Id"));
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().string("Incorrect Id"));
    }
}
