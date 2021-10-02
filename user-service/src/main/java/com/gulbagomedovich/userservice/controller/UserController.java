package com.gulbagomedovich.userservice.controller;

import com.gulbagomedovich.userservice.dto.UserDto;
import com.gulbagomedovich.userservice.service.UserService;
import com.gulbagomedovich.userservice.vo.UserWithDepartmentVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserWithDepartmentVo> readUserWithDepartment(@PathVariable("userId") Long userId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.readUserWithDepartment(userId));
    }
}
