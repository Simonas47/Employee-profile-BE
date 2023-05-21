package com.sourcery.employeeprofile.dto;

import com.sourcery.employeeprofile.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateEmployeeDto {
    private String name;
    private String surname;
    private String middleName;
    private String email;
    private String password;
    private Integer titleId;
    private String status;
    private Boolean isManager;
    private Image image;
}
