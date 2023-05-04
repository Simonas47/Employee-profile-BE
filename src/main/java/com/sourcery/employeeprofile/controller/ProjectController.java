package com.sourcery.employeeprofile.controller;


import com.sourcery.employeeprofile.dto.AddProjectEmployeeResponsibilitiesDto;
import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.dto.ProjectDto;

import com.sourcery.employeeprofile.dto.ProjectEmployeeResponsibilitiesDto;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.service.ProjectService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<ProjectEmployee>> createNewProjectRelationship(@RequestPart("projectId") Integer projectId,
                                                                              @RequestPart("employeeId") Integer employeeId,
                                                                              @RequestPart("projectEmployeeStatus") String projectEmployeeStatus,
                                                                              @RequestPart("projectEmployeeStartDate") Date projectEmployeeStartDate,
                                                                              @RequestPart("projectEmployeeEndDate") Date projectEmployeeEndDate) {
        return ResponseEntity.ok(projectService.createNewProjectRelationship(projectId, employeeId, projectEmployeeStatus, projectEmployeeStartDate, projectEmployeeEndDate));
    }

    @GetMapping(value = "/relationships/byProject/{projectId}", produces = "application/json")
    public ResponseEntity<List<ProjectEmployee>> getProjectRelationshipsByProjectId(@PathVariable Integer projectId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectRelationshipsByProjectId(projectId));
    }

    @PatchMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<ProjectDto> deleteProjectById(@PathVariable Integer id) {
        return projectService
                .deleteProjectById(id)
                .map(projectDto -> ResponseEntity.ok(projectDto))
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/getProjectEmployeeBy/{id}", produces = "application/json")
    public ResponseEntity<List<ProjectEmployeeResponsibilitiesDto>> getProjectEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectEmployeeById(id));
    }

    @GetMapping(value = "/projectsByEmployeeId/{employeeId}", produces = "application/json")
    public ResponseEntity<List<ProjectEmployee>> getProjectRelationshipsByEmployeeId(@PathVariable Integer
                                                                                             employeeId) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getProjectRelationshipsByEmployeeId(employeeId));
    }

    @PostMapping("/addTitleToProjectEmployee")
    public ResponseEntity<String> addTitleToProjectEmployee
            (@RequestBody @NotNull Map<String, Object> requestBody) {
        Integer projectId = Integer.valueOf((String) requestBody.get("projectId"));
        Integer employeeId = Integer.valueOf((String) requestBody.get("employeeId"));
        Integer titleId = Integer.valueOf((String) requestBody.get("titleId"));

        int rowsAffected = projectService.addProjectEmployeesTitle(projectId, employeeId, titleId);

        if (rowsAffected == 1) {
            return ResponseEntity.ok("Title added successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to add title");
        }
    }

    @PostMapping("/addResponsibilitiesToProjectEmployee")
    public ResponseEntity<Integer> addResponsibilitiesToProjectEmployee(@RequestBody @NotNull AddProjectEmployeeResponsibilitiesDto requestDto) {
        return ResponseEntity.ok(projectService.addProjectEmployeeResponsibilities(requestDto.getProjectId(), requestDto.getEmployeeId(), requestDto.getResponsibilities()));
    }

    @GetMapping(value = "/responsibilities/{projectId}/{employeeId}", produces = "text/plain")
    public ResponseEntity<String> getResponsibilitiesByProjectAndEmployee(
            @PathVariable Integer projectId,
            @PathVariable Integer employeeId) {
        String responsibilities = projectService.getProjectResponsibilitiesByProjectAndEmployee(projectId, employeeId);
        if (responsibilities == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responsibilities);
    }
}
