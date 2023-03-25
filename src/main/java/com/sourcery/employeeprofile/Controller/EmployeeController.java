package com.sourcery.employeeprofile.Controller;
import com.sourcery.employeeprofile.Dto.EmployeeDto;
import com.sourcery.employeeprofile.Model.Employee;
import com.sourcery.employeeprofile.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;
import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;


@RestController
@RequestMapping(value = BASE_URL + "/employee")
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
}
