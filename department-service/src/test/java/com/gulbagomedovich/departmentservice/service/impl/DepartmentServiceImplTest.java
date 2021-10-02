package com.gulbagomedovich.departmentservice.service.impl;

import com.gulbagomedovich.departmentservice.dto.DepartmentDto;
import com.gulbagomedovich.departmentservice.entity.Department;
import com.gulbagomedovich.departmentservice.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private static final Department DEPARTMENT = new Department();
    private static final DepartmentDto DEPARTMENT_DTO = new DepartmentDto();

    @BeforeEach
    void setUp() {
        lenient().doReturn(DEPARTMENT).when(modelMapper).map(any(DepartmentDto.class), eq(Department.class));
        lenient().doReturn(DEPARTMENT_DTO).when(modelMapper).map(any(Department.class), eq(DepartmentDto.class));
        lenient().doReturn(DEPARTMENT).when(departmentRepository).save(any(Department.class));
        lenient().doReturn(DEPARTMENT).when(departmentRepository).findDepartmentById(anyLong());
    }

    @Test
    void testCreateDepartment_modelMapper_expected_call_map() {
        departmentService.createDepartment(DEPARTMENT_DTO);

        verify(modelMapper).map(eq(DEPARTMENT_DTO), eq(Department.class));
    }

    @Test
    void testCreateDepartment_departmentRepository_expected_call_save() {
        departmentService.createDepartment(DEPARTMENT_DTO);

        verify(departmentRepository).save(eq(DEPARTMENT));
    }

    @Test
    void testReadDepartment_departmentRepository_expected_call_findDepartmentById() {
        departmentService.readDepartment(1L);

        verify(departmentRepository).findDepartmentById(eq(1L));
    }

    @Test
    void testReadDepartment_modelMapper_expected_call_map() {
        departmentService.readDepartment(1L);

        verify(modelMapper).map(eq(DEPARTMENT), eq(DepartmentDto.class));
    }
}