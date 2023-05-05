package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/users")
public class UserController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<EmployeeDto> getCurrentUser(@AuthenticationPrincipal Jwt accessToken) {
        Object emailFromToken = accessToken.getClaim("employee-profile.email");
        return employeeService.getByEmail(emailFromToken.toString())
                .map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElse(ResponseEntity.notFound().build());
    }
}
