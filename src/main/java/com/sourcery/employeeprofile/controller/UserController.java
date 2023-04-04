package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeePageDto;
import com.sourcery.employeeprofile.dto.SearchUserPageDto;
import com.sourcery.employeeprofile.dto.UserDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.User;
import com.sourcery.employeeprofile.service.EmployeeService;
import com.sourcery.employeeprofile.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService userService;

    public static final int DEFAULT_PAGE_SIZE = 100;
    public static final int MINIMAL_PAGE_SIZE = 10;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<UserDto> createNewUser(@RequestPart("email") User user) {
        try {
            return ResponseEntity.ok(userService.createNewUser(user));
        } catch (IOException u) {
            System.out.println(u.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/search", params = {"name", "page", "size"}, produces = "application/json")
    public ResponseEntity<SearchUserPageDto> searchByName(@RequestParam(value = "name", required = true) String name,
                                                          @RequestParam(value = "page", required = false) Integer page,
                                                          @RequestParam(value = "size", required = false) Integer size) {
        if (size == -1) size = DEFAULT_PAGE_SIZE;
        else if (size == null || size < MINIMAL_PAGE_SIZE) size = MINIMAL_PAGE_SIZE;
        if (page == null || page < 0) page = 0;


        List<UserDto> users = userService.getUsers(name, ++page, size);
        Integer userCount = userService.getUsersCountByEmail(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SearchUserPageDto(userCount, users));
    }

    @GetMapping(value = "/get/{email}", produces = "application/json")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return userService.getByEmail(email)
                .map(userDto -> ResponseEntity.ok(userDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/get", produces = "application/json")
    public ResponseEntity<Map<String, String>> getByEmailAndPassword(@RequestBody Map<String, String> payload, HttpServletResponse response) throws IOException {
        String email = payload.get("email");
        String password = payload.get("password");
        if (email == null || password == null) {
            return ResponseEntity.badRequest().build();
        }
        Optional<UserDto> user = userService.getByEmailAndPassword(email, password);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
            // Redirect to the other page
        }
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("redirectUrl", "http://localhost:3000/some-page");
        return ResponseEntity.ok(responseBody);
    }
}
