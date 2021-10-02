package com.gulbagomedovich.userservice.service;

import com.gulbagomedovich.userservice.dto.UserDto;
import com.gulbagomedovich.userservice.vo.UserWithDepartmentVo;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserWithDepartmentVo readUserWithDepartment(Long userId);
}
