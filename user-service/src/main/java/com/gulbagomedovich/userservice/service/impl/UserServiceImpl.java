package com.gulbagomedovich.userservice.service.impl;

import com.gulbagomedovich.userservice.dto.DepartmentDto;
import com.gulbagomedovich.userservice.dto.UserDto;
import com.gulbagomedovich.userservice.entity.User;
import com.gulbagomedovich.userservice.repository.UserRepository;
import com.gulbagomedovich.userservice.service.UserService;
import com.gulbagomedovich.userservice.vo.UserWithDepartmentVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        log.info("Создан новый пользователь");
        return userDto;
    }

    @Override
    public UserWithDepartmentVo readUserWithDepartment(Long userId) {
        UserWithDepartmentVo userWithDepartmentVo = new UserWithDepartmentVo();
        UserDto userDto = modelMapper.map(userRepository.findUserById(userId), UserDto.class);
        DepartmentDto departmentDto = restTemplate.getForObject("http://department-service/departments/" +
                userDto.getDepartmentId(), DepartmentDto.class);
        userWithDepartmentVo.setUserDto(userDto);
        userWithDepartmentVo.setDepartmentDto(departmentDto);
        return userWithDepartmentVo;
    }
}
