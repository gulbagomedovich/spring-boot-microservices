package com.gulbagomedovich.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {
    @GetMapping("/departmentServiceFallBack")
    public String departmentServiceFallBackMethod() {
        return "Служба отдела не доступна";
    }

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "Служба пользователя не доступна";
    }
}
