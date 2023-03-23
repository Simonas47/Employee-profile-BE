package com.sourcery.employeeprofile.Dto;

import com.sourcery.employeeprofile.Model.Image;
import com.sourcery.employeeprofile.Model.Title;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmployeeDto {
    private UUID id;
    private String name;
    private String surname;
    private String middle_name;
    private Date hiring_date;
    private Date exit_date;
    private String image_name;
    private String image_type;
    private byte[] image_bytes;
    private String title;

}
