package com.gulbagomedovich.departmentservice.repository;

import com.gulbagomedovich.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentById(Long id);
}
