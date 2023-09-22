package com.example.demo.Employee;

import com.example.demo.controller.EmployeeController;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.EmployeeService;
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
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private DepartmentEntity departmentEntity;
    private ManagerEntity managerEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    ManagerEntity Manager1=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    EmployeeEntity Employee1=new EmployeeEntity(1L, "rama", "1233", "1233", "AAA", "A", Department1,Manager1);
    @Test
    public void Update_Employee_Test()throws Exception {
//        String departmentJson = objectMapper.writeValueAsString(Department1);
//        String managerJson = objectMapper.writeValueAsString(Manager1);
        when(employeeService.update(any(EmployeeEntity.class))).thenReturn(Employee1);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\":\"shyam\",\n" +
                                "    \"contact\":\"1233\",\n" +
                                "    \"email\":\"1233\",\n" +
                                "    \"address\":\"AAA\",\n" +
                                "    \"status\":\"A\",\n" +
                                "    \"department\":{\n" +
                                "        \"id\":1\n" +
                                "    },\n" +
                                "    \"manager\":{\n" +
                                "        \"id\":1\n" +
                                "    }\n" +
                                "}"))

                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("rama"));

    }
}
