package com.gulbagomedovich.departmentservice.service.impl;

import com.gulbagomedovich.departmentservice.dto.DepartmentDto;
import com.gulbagomedovich.departmentservice.entity.Department;
import com.gulbagomedovich.departmentservice.repository.DepartmentRepository;
import com.gulbagomedovich.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        departmentRepository.save(department);
        log.info("Создан новый отдел");
        return departmentDto;
    }

    @Override
    public DepartmentDto readDepartment(Long departmentId) {
        Department department = departmentRepository.findDepartmentById(departmentId);
        return modelMapper.map(department, DepartmentDto.class);
    }
}
