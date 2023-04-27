package com.sourcery.employeeprofile.service;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.SearchEmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.model.Image;
import com.sourcery.employeeprofile.repository.EmployeeRepository;
import com.sourcery.employeeprofile.repository.EmploymentDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmploymentDateRepository employmentDateRepository;
    @Autowired
    ImageService imageService;

    public EmployeeDto createNewEmployee(Employee employee, MultipartFile file) throws IOException {
        Image newImage = imageService.createNewImage(file);
        employee.setImageId(newImage.getId());
        employeeRepository.createNewEmployee(employee);
        if (employee.getEmploymentDates() != null && employee.getEmploymentDates().size() > 0)
            employmentDateRepository.setEmploymentDates(employee.getId(), employee.getEmploymentDates());
        return this.getById(employee.getId()).orElseThrow(IllegalStateException::new);
    }

    public Optional<EmployeeDto> getById(Integer id) {
        Optional<EmployeeDto> employee = employeeRepository.getById(id);
        if (employee.isPresent()) {
            List<EmploymentDate> employmentDates = employmentDateRepository.getEmploymentDates(id);
            employee.get().setEmploymentDates(employmentDates);
        }
        return employee;
    }

    public List<SearchEmployeeDto> getEmployees(String searchValue, Integer page, Integer size, Boolean isLimited) {
        String nameLike = "%" + searchValue + "%";
        return employeeRepository.getEmployees(nameLike, page, size, isLimited);
    }

    public Integer getEmployeeCountByName(String searchValue) {
        String nameLike = "%" + searchValue + "%";
        return employeeRepository.getEmployeeCountByName(nameLike);
    }
}
