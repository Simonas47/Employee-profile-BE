package com.sourcery.employeeprofile.controller;


import com.sourcery.employeeprofile.dto.AddProjectEmployeeResponsibilitiesDto;
import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.dto.ProjectEmployeeErrorDto;

import com.sourcery.employeeprofile.dto.MyProjectDto;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.service.NotificationService;
import com.sourcery.employeeprofile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    NotificationService notificationService;

    @PostMapping()
    public ResponseEntity<Object> createNewProject(@RequestBody ProjectDto project) {
        List<ProjectEmployeeErrorDto> projectEmployeeErrors = projectService.validateProjectEmployees(project);
        if (!projectEmployeeErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(projectEmployeeErrors);
        }
        try {
            ProjectDto newProject = projectService.createNewProject(project);
            return ResponseEntity.ok(newProject);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<Object> updateProject(@RequestBody ProjectDto project) {
        List<ProjectEmployeeErrorDto> projectEmployeeErrors = projectService.validateProjectEmployees(project);
        if (!projectEmployeeErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(projectEmployeeErrors);
        }
        try {
            ProjectDto updatedProject = projectService.updateProject(project);
            return ResponseEntity.ok(updatedProject);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Integer id) {
        return projectService
                .getProjectById(id)
                .map(projectDto -> ResponseEntity.ok(projectDto))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @PostMapping(value = "/addEmployee")
    public ResponseEntity<List<ProjectEmployee>> createNewProjectRelationship(
            @RequestPart("projectId") Integer projectId,
            @RequestPart("employeeId") Integer employeeId,
            @RequestPart("projectEmployeeStartDate") Date projectEmployeeStartDate,
            @RequestPart("projectEmployeeEndDate") Date projectEmployeeEndDate
    ) {
        return ResponseEntity.ok(projectService.createNewProjectRelationship(
                projectId,
                employeeId,
                projectEmployeeStartDate,
                projectEmployeeEndDate
        ));
    }

    @GetMapping(value = "/relationships/byProject/{projectId}", produces = "application/json")
    public ResponseEntity<List<ProjectEmployee>> getProjectRelationshipsByProjectId(@PathVariable Integer projectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectRelationshipsByProjectId(projectId));
    }

    @PatchMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<ProjectDto> deleteProjectById(@PathVariable Integer id) {
        ResponseEntity<ProjectDto> deletedProject = projectService
                .deleteProjectById(id)
                .map(projectDto -> ResponseEntity.ok(projectDto))
                .orElse(ResponseEntity.notFound().build());
        notificationService.deleteByProjectId(id);
        return deletedProject;
    }

    @GetMapping(value = "/getMyProjectsByEmployee/{id}", produces = "application/json")
    public ResponseEntity<List<MyProjectDto>> getMyProjectsByEmployeeId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getMyProjectsByEmployeeId(id));
    }

    @PostMapping("/setMyProjectEmployeeResponsibilities")
    public ResponseEntity<Integer> setMyProjectEmployeeResponsibilities   (@RequestBody AddProjectEmployeeResponsibilitiesDto requestDto) {
        return ResponseEntity.ok(projectService.setMyProjectEmployeeResponsibilities(requestDto.getProjectId(), requestDto.getEmployeeId(), requestDto.getResponsibilities()));
    }
}
