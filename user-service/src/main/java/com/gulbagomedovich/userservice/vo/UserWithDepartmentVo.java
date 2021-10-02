package com.gulbagomedovich.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.userservice.dto.DepartmentDto;
import com.gulbagomedovich.userservice.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserWithDepartmentVo {
    private UserDto userDto;
    private DepartmentDto departmentDto;
}
