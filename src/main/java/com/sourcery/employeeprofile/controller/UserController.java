package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.UserDto;
import com.sourcery.employeeprofile.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/get", produces = "application/json")
    public ResponseEntity<Map<String, String>> getByEmailAndPassword(@RequestBody Map<String, String> payload, HttpServletResponse response) throws IOException {
        String email = payload.get("email");
        String password = payload.get("password");
        if (email == null || password == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<UserDto> user = userService.getByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("redirectUrl", "http://localhost:3000/some-page");
        return ResponseEntity.ok(responseBody);
    }
}
