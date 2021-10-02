package com.gulbagomedovich.departmentservice.controller;

import com.gulbagomedovich.departmentservice.dto.DepartmentDto;
import com.gulbagomedovich.departmentservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.createDepartment(departmentDto));
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> readDepartment(@PathVariable("departmentId") Long departmentId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(departmentService.readDepartment(departmentId));
    }
}
