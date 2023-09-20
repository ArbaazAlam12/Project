package com.example.demo.Employee;

import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.EmployeeService;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindTest {
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(employeeService);
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    ManagerEntity Manager1=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    EmployeeEntity Employee1=new EmployeeEntity(1L, "rama", "1233", "1233", "AAA", "A", Department1,Manager1);
    EmployeeEntity Employee2=new EmployeeEntity(2L, "aaaa", "1233", "1233", "AAA", "A", Department1,Manager1);
    EmployeeDTO Employee3=new EmployeeDTO(3L, "shyam", "1233", "1233", "A", Department1, Manager1);
    @Test
    public void Get_All_Employee() throws  Exception{
        List<EmployeeEntity> employeeEntityList = new ArrayList<>(Arrays.asList(Employee1, Employee2));
        when(employeeService.findAll()).thenReturn(employeeEntityList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/findAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))
                );
    }
    @Test
    public void Get_By_Employee_Id() throws  Exception{
        when(employeeService.findById(3L)).thenReturn(Employee3);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/findById/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("shyam")
                );
    }
}

