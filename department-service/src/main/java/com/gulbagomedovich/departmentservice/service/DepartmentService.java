package com.gulbagomedovich.departmentservice.service;

import com.gulbagomedovich.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto readDepartment(Long departmentId);
}
