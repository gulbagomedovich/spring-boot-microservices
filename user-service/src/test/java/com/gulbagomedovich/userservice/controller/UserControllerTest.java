package com.gulbagomedovich.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulbagomedovich.userservice.dto.UserDto;
import com.gulbagomedovich.userservice.service.UserService;
import com.gulbagomedovich.userservice.vo.UserWithDepartmentVo;
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

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private static final UserDto USER_DTO = new UserDto();
    private static final UserWithDepartmentVo USER_WITH_DEPARTMENT_VO = new UserWithDepartmentVo();

    @BeforeEach
    void setUp() {
        lenient().doReturn(USER_DTO).when(userService).createUser(any(UserDto.class));
        lenient().doReturn(USER_WITH_DEPARTMENT_VO).when(userService).readUserWithDepartment(anyLong());
    }

    @Test
    void testCreateUser_userService_expected_call_createUser() throws Exception {
        mockMvc
                .perform(post("/users/")
                        .content(objectMapper.writeValueAsString(USER_DTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        verify(userService).createUser(eq(USER_DTO));
    }

    @Test
    void testReadUserWithDepartment_userService_expected_call_readUserWithDepartment() throws Exception {
        mockMvc
                .perform(get("/users/1"));

        verify(userService).readUserWithDepartment(eq(1L));
    }
}