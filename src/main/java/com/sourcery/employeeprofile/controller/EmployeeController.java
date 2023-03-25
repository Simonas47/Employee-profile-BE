package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;


@RestController
@RequestMapping(value = BASE_URL + "/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDto> create(@RequestPart("employee") Employee employee,
                                              @RequestPart("image") MultipartFile image) {

        try {
            return ResponseEntity.ok(employeeService.create(employee, image));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id) {
        return employeeService.findById(id)
                .map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> searchByEmployeeName(@RequestParam(name = "name") String searchValue) {
        List<EmployeeDto> employees = employeeService.getAll();
        List<EmployeeDto> employeesResult = new ArrayList<>();
        employees.forEach(employeeDto -> {
            String fullName;
            if (employeeDto.getMiddleName() != null) {
                fullName = employeeDto.getName() + " " + employeeDto.getMiddleName() + " " + employeeDto.getSurname();
            } else {
                fullName = employeeDto.getName() + " " + employeeDto.getSurname();
            }
            if (fullName.toLowerCase().contains(searchValue.toLowerCase())) {
                employeesResult.add(employeeDto);
            }
        });
        if (employeesResult.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(employeesResult);
    }
}
