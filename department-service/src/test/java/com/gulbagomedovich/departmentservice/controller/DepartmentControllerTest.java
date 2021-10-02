package com.gulbagomedovich.departmentservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulbagomedovich.departmentservice.dto.DepartmentDto;
import com.gulbagomedovich.departmentservice.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DepartmentService departmentService;

    private static final DepartmentDto DEPARTMENT_DTO = new DepartmentDto();

    @BeforeEach
    void setUp() {
        lenient().doReturn(DEPARTMENT_DTO).when(departmentService).createDepartment(any(DepartmentDto.class));
        lenient().doReturn(DEPARTMENT_DTO).when(departmentService).readDepartment(anyLong());
    }

    @Test
    void testCreateDepartment_departmentService_expected_call_createDepartment() throws Exception {
        mockMvc
                .perform(post("/departments/")
                        .content(objectMapper.writeValueAsString(DEPARTMENT_DTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        verify(departmentService).createDepartment(eq(DEPARTMENT_DTO));
    }

    @Test
    void testReadDepartment_departmentService_expected_call_readDepartment() throws Exception {
        mockMvc
                .perform(get("/departments/1"));

        verify(departmentService).readDepartment(eq(1L));
    }
}