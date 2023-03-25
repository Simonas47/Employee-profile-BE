package com.sourcery.employeeprofile.config;

import com.sourcery.employeeprofile.EmployeeProfileApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackageClasses = EmployeeProfileApplication.class)
public class MybatisConfig {

}
