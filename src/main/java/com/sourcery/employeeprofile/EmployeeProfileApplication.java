package com.sourcery.employeeprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeProfileApplication {
	public static final String BASE_URL = "/api";

	public static void main(String[] args) {
		SpringApplication.run(EmployeeProfileApplication.class, args);
	}

}
