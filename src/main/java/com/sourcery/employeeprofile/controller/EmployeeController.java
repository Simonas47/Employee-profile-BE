package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeePageDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;
import static com.sourcery.employeeprofile.repository.sqlprovider.EmployeeSqlProvider.DEFAULT_LIMIT;

@RestController
@RequestMapping(value = BASE_URL + "/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
    public ResponseEntity<EmployeeDto> createNewEmployee(@RequestPart("employee") Employee employee,
                                                         @RequestPart("image") MultipartFile image) {
        try {
            return ResponseEntity.ok(employeeService.createNewEmployee(employee, image));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/search", params = {"name", "page", "size"}, produces = "application/json")
    public ResponseEntity<SearchEmployeePageDto> searchByName(@RequestParam(value = "name", required = false) String name,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "size", required = false) Integer size) {
        page++;
        if (size == -1) size = DEFAULT_LIMIT;
        else if (size == null || size < 10) size = 10;
        if (page == null || page < 1) page = 1;

        List<EmployeeDto> employees = employeeService.getEmployees(name, page, size);
        Integer employeeCount = employeeService.getEmployeeCountByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SearchEmployeePageDto(employeeCount, employees));
    }
        @GetMapping(value = "/get/{id}", produces = "application/json")
        public ResponseEntity<EmployeeDto> getById (@PathVariable UUID id){
            return employeeService.getById(id)
                    .map(employeeDto -> ResponseEntity.ok(employeeDto))
                    .orElse(ResponseEntity.notFound().build());
        }

    }
