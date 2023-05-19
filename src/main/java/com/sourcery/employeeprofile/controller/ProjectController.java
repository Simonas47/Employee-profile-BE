package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.AddProjectEmployeeResponsibilitiesDto;
import com.sourcery.employeeprofile.dto.MyProjectDto;
import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.dto.ProjectEmployeeErrorDto;
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

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @PatchMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<ProjectDto> deleteProjectById(@PathVariable Integer id) {
        ResponseEntity<ProjectDto> deletedProject = projectService
                .deleteProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        notificationService.deleteByProjectId(id);
        return deletedProject;
    }

    @GetMapping(value = "/getMyProjectsByEmployee/{id}", produces = "application/json")
    public ResponseEntity<List<MyProjectDto>> getMyProjectsByEmployeeId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getMyProjectsByEmployeeId(id));
    }

    @PostMapping("/updateMyProject")
    public ResponseEntity<Integer> updateMyProject(@RequestBody AddProjectEmployeeResponsibilitiesDto requestDto) {
        return ResponseEntity.ok(projectService.updateMyProject(requestDto));
    }
}
