package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ImageService imageService;

    public EmployeeDto createNewEmployee(Employee employee, MultipartFile file) throws IOException {
        Image newImage = imageService.createNewImage(file);
        employee.setImageId(newImage.getId());
        employeeRepository.createNewEmployee(employee);
        return this.getById(employee.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<EmployeeDto> getById(UUID id) {
        return employeeRepository.getById(id);
    }

    public List<EmployeeDto> getEmployees(String searchValue, Integer page, Integer size) {
        String nameLike = "%" + searchValue + "%";
        return employeeRepository.getEmployees(nameLike, page, size);
    }

    public Integer getEmployeeCount(String searchValue) {
        String nameLike = "%" + searchValue + "%";
        return employeeRepository.getEmployeeCount(nameLike);
    }
}
