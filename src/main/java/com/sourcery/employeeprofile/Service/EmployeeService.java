package com.sourcery.employeeprofile.Service;

import com.sourcery.employeeprofile.Dto.EmployeeDto;
import com.sourcery.employeeprofile.Model.Employee;
import com.sourcery.employeeprofile.Model.Image;
import com.sourcery.employeeprofile.Repository.EmployeeRepository;
import com.sourcery.employeeprofile.Service.Mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ImageService imageService;
    @Autowired
    EmployeeMapper employeeMapper;

    public EmployeeDto create(Employee employee, MultipartFile file) throws IOException {
        Image newImage = imageService.uploadImage(file);
        UUID newImageId = newImage.getId();
        employee.setImage_id(newImageId);
        UUID newId = UUID.randomUUID();
        employee.setId(newId);
        employeeRepository.create(employee);
        return this.findById(newId).orElseThrow(IllegalStateException::new);
    }

    public Optional<EmployeeDto> findById(UUID id)
    {
        return employeeRepository.findById(id);
    }
}
