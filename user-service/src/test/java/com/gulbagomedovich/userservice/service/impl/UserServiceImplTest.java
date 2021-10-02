package com.gulbagomedovich.userservice.service.impl;

import com.gulbagomedovich.userservice.dto.DepartmentDto;
import com.gulbagomedovich.userservice.dto.UserDto;
import com.gulbagomedovich.userservice.entity.User;
import com.gulbagomedovich.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserServiceImpl userService;

    private static final User USER = new User();
    private static final UserDto USER_DTO = new UserDto();
    private static final DepartmentDto DEPARTMENT_DTO = new DepartmentDto();

    @BeforeEach
    void setUp() {
        lenient().doReturn(USER).when(modelMapper).map(any(UserDto.class), eq(User.class));
        lenient().doReturn(USER_DTO).when(modelMapper).map(any(User.class), eq(UserDto.class));
        lenient().doReturn(USER).when(userRepository).save(any(User.class));
        lenient().doReturn(USER).when(userRepository).findUserById(anyLong());
        lenient().doReturn(DEPARTMENT_DTO).when(restTemplate).getForObject(anyString(), eq(DepartmentDto.class));
    }

    @Test
    void testCreateUser_modelMapper_expected_call_map() {
        userService.createUser(USER_DTO);

        verify(modelMapper).map(eq(USER_DTO), eq(User.class));
    }

    @Test
    void testCreateUser_userRepository_expected_call_save() {
        userService.createUser(USER_DTO);

        verify(userRepository).save(eq(USER));
    }

    @Test
    void testReadUserWithDepartment_userRepository_expected_call_findUserById() {
        userService.readUserWithDepartment(1L);

        verify(userRepository).findUserById(eq(1L));
    }

    @Test
    void testReadUserWithDepartment_modelMapper_expected_call_map() {
        userService.readUserWithDepartment(1L);

        verify(modelMapper).map(eq(USER), eq(UserDto.class));
    }

    @Test
    void testReadUserWithDepartment_restTemplate_expected_call_getForObject() {
        userService.readUserWithDepartment(1L);

        verify(restTemplate).getForObject(contains("1"), eq(DepartmentDto.class));
    }
}