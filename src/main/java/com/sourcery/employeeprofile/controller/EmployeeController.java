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
import java.util.List;
import java.util.UUID;
import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

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

    @GetMapping(value = "/search", params = { "name", "page", "size"})
    public ResponseEntity<List<EmployeeDto>> searchByName(@RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "page", required = false) Integer page,
                                                          @RequestParam(value = "size", required = false) Integer size) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeService.getEmployees(name, page, size));
    }
    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id) {
        return employeeService.getById(id)
                .map(employeeDto -> ResponseEntity.ok(employeeDto))
                .orElse(ResponseEntity.notFound().build());
    }

}
