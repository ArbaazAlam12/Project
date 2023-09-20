package com.example.demo.Employee;

import com.example.demo.controller.EmployeeController;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.ManagerEntity;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignNewManagerTest {
    private MockMvc mockMvc;

    public class JSONUtils {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        public static String toJson(Object object) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks( this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    DepartmentEntity Department1 = new DepartmentEntity(1L, "RTE");
    ManagerEntity Manager1=new ManagerEntity(1L, "arbaaz", "1233", "12333", "A", Department1);
    ManagerEntity Manager2=new ManagerEntity(2L, "asdas", "1233", "12333", "A", Department1);

    EmployeeEntity Employee1=new EmployeeEntity(1L, "rama", "1233", "1233", "AAA", "A", Department1,Manager1);
    EmployeeEntity Employee2=new EmployeeEntity(2L, "shyam", "1233", "1233", "AAA", "NA", Department1,Manager1);
    EmployeeEntity Employee3=new EmployeeEntity(1L, "rama", "1233", "1233", "AAA", "A", Department1,Manager2);
    EmployeeEntity Employee4=new EmployeeEntity(2L, "shyam", "1233", "1233", "AAA", "NA", Department1,Manager2);

    List<EmployeeEntity> employeeEntityList= new ArrayList<>(Arrays.asList(Employee1, Employee2));
    List<EmployeeEntity> employeeEntityList1= new ArrayList<>(Arrays.asList(Employee3, Employee4));
    List<Long> employeeIds = Arrays.asList(Employee1.getId(), Employee2.getId());
    Map<String, List<Long>> requestBody = new HashMap<>();
    @Test
    public void Assign_All_Employee_New_Manager() throws  Exception{
        requestBody.put("employeeId", employeeIds);
        when(employeeService.assignEmployeeToManagerId(employeeIds,Manager2.getId())).thenReturn(employeeEntityList1);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/assignToManager/2")
                        .contentType(MediaType.APPLICATION_JSON)
                            .content(JSONUtils.toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].manager.id").value(2L)) // Check the manager ID of the first employee
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].manager.id").value(2L));

    }

    @Test
    public void Assign_All_Employee_New_Manager_Error() throws  Exception{
        requestBody.put("employeeId", employeeIds);
        when(employeeService.assignEmployeeToManagerId(employeeIds,Manager2.getId())).thenThrow(new RuntimeException("Error assigning the Id"));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee/assignToManager/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtils.toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andExpect(MockMvcResultMatchers.content().string("Error assigning the Id"));
    }
}
