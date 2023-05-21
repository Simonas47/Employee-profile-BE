package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.model.Title;
import com.sourcery.employeeprofile.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/titles")
public class TitleController {
    @Autowired
    TitleService titleService;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<Title>> getAllTitles() {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.getAllTitles());
    }
}
