package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/project")
@CrossOrigin(origins = {"http://localhost:3000", "https://employee-profile.devbstaging.com"})
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping()
    public ResponseEntity<ProjectDto> createNewProject(@RequestBody ProjectDto project) {
        try {
            return ResponseEntity.ok(projectService.createNewProject(project));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping()
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto project) {
        try {
            return ResponseEntity.ok(projectService.updateProject(project));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable UUID id) {
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
    public ResponseEntity<List<ProjectEmployee>> createNewProjectRelationship(@RequestPart("projectId") UUID projectId,
                                                                              @RequestPart("employeeId") UUID employeeId,
                                                                              @RequestPart("projectEmployeeStatus") String projectEmployeeStatus,
                                                                              @RequestPart("projectEmployeeStartDate") Date projectEmployeeStartDate,
                                                                              @RequestPart("projectEmployeeEndDate") Date projectEmployeeEndDate) {
        return ResponseEntity.ok(projectService.createNewProjectRelationship(projectId, employeeId, projectEmployeeStatus, projectEmployeeStartDate, projectEmployeeEndDate));
    }

    @GetMapping(value = "/relationships/byProject/{projectId}", produces = "application/json")
    public ResponseEntity<List<ProjectEmployee>> getProjectRelationshipsByProjectId(@PathVariable UUID projectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectRelationshipsByProjectId(projectId));
    }

    @PatchMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<ProjectDto> deleteProjectById(@PathVariable UUID id) {
        return projectService
                .deleteProjectById(id)
                .map(projectDto -> ResponseEntity.ok(projectDto))
                .orElse(ResponseEntity.notFound().build());
    }
}
