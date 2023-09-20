package com.example.demo;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.services.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.example.demo.entities.DepartmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = ApplicationArguments.class)
@SpringJUnitConfig

@AutoConfigureMockMvc
class ProjectApplicationTests {
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService departmentService;

	private ObjectMapper objectMapper; // Used for JSON serialization/deserialization

	public ProjectApplicationTests(ObjectMapper objectMapper, MockMvc mockMvc) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void testSaveDepartment() throws Exception {
		// Create a sample DepartmentEntity object
		DepartmentEntity department = new DepartmentEntity();
		department.setId(1L);
		department.setName("HR Department");

		// Mock the behavior of the departmentService.save method
		when(departmentService.save(any(DepartmentEntity.class))).thenReturn(department);

		// Perform a POST request to the /departments/save endpoint with JSON content
		mockMvc.perform(MockMvcRequestBuilders.post("/department/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"HR Department\"}")) // JSON content
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)) // Verify the response JSON
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("HR Department"));
	}

}
