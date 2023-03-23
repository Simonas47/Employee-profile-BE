package com.sourcery.employeeprofile.Model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private UUID id;
    private String name;
    private String surname;
    private String middle_name;
    private Date hiring_date;
    private Date exit_date;
    private UUID image_id;
    private UUID title_id;
}
