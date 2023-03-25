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


    public EmployeeDto create(Employee employee, MultipartFile file) throws IOException {
        Image newImage = imageService.uploadImage(file);
        employee.setImageId(newImage.getId());

        employeeRepository.create(employee);

        return this.findById(employee.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<EmployeeDto> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    public List<EmployeeDto> getAll() {
        return employeeRepository.getAll();
    }
}
