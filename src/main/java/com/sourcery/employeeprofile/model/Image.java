package com.sourcery.employeeprofile.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Image {
    private UUID id;
    private String name;
    private String type;
    private String bytes;
}
