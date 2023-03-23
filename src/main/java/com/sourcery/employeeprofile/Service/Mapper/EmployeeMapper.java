package com.sourcery.employeeprofile.Service.Mapper;

import com.sourcery.employeeprofile.Dto.EmployeeDto;
import com.sourcery.employeeprofile.Model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public static Employee toEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .surname(employeeDto.getSurname())
                .middle_name(employeeDto.getMiddle_name())
                .hiring_date(employeeDto.getHiring_date())
                .exit_date(employeeDto.getExit_date())
                .build();
    }

    public EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .middle_name(employee.getMiddle_name())
                .hiring_date(employee.getHiring_date())
                .exit_date(employee.getExit_date())
                .build();
    }
}
