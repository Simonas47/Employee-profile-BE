package com.sourcery.employeeprofile.Controller;

import com.sourcery.employeeprofile.Dto.EmployeeDto;
import com.sourcery.employeeprofile.Model.Employee;
import com.sourcery.employeeprofile.Service.EmployeeService;
import com.sourcery.employeeprofile.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@RequestMapping(value = EmployeeController.QUIZ_ENDPOINT)
public class EmployeeController {
    public static final String QUIZ_ENDPOINT = "/employee";
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ImageService imageService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EmployeeDto> create(@RequestPart("employee") Employee employee,
                                              @RequestPart("image") MultipartFile picFile) {

        try {
            EmployeeDto newEmployee = employeeService.create(employee, picFile);
            return ResponseEntity.ok(newEmployee);
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
}
