package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.CreateEmployeeDto;
import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeePageDto;
import com.sourcery.employeeprofile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    public static final int DEFAULT_PAGE_SIZE = 100;
    public static final int MINIMAL_PAGE_SIZE = 10;

    @PostMapping(produces = "application/json")
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestBody CreateEmployeeDto employee) {
        try {
            return ResponseEntity.ok(employeeService.createNewEmployee(employee));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<SearchEmployeePageDto> searchByNameSkillsAchievements(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "skills", required = false, defaultValue = "") List<Integer> selectedSkillsIds,
            @RequestParam(value = "achievements", required = false, defaultValue = "") List<Integer> selectedAchievementsIds,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "isLimited", required = false) Boolean isLimited
    ) {
        if (size == -1) size = DEFAULT_PAGE_SIZE;
        else if (size == null || size < MINIMAL_PAGE_SIZE) size = MINIMAL_PAGE_SIZE;
        if (page == null || page < 0) page = 0;
        if (isLimited == null) isLimited = true;

        List<SearchEmployeeDto> employees = employeeService.getEmployees(
                name,
                selectedSkillsIds,
                selectedAchievementsIds,
                ++page,
                size,
                isLimited
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new SearchEmployeePageDto(employees.size(), employees));
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Integer id) {
        return employeeService
                .getById(id)
                .map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElse(ResponseEntity.notFound().build());
    }
}
