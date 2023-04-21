package com.sourcery.employeeprofile.controller;

import com.sourcery.employeeprofile.dto.ProjectDto;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.sourcery.employeeprofile.EmployeeProfileApplication.BASE_URL;

@RestController
@RequestMapping(value = BASE_URL + "/project")
@CrossOrigin(origins = "http://localhost:3000")
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
        System.out.println("Received request to get project with ID: " + id);
        return projectService
                .getProjectById(id)
                .map(projectDto -> {
                    System.out.println("Returning project details for project with ID: " + id);
                    return ResponseEntity.ok(projectDto);
                })
                .orElseGet(() -> {
                    System.out.println("Project with ID " + id + " not found");
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @PostMapping(value = "/addEmployee")
    public ResponseEntity<List<ProjectEmployee>> createNewProjectRelationship(@RequestPart("projectId") UUID projectId,
                                                                              @RequestPart("employeeId") UUID employeeId,
                                                                              @RequestPart("teamMemberStatus") String teamMemberStatus) {
        return ResponseEntity.ok(projectService.createNewProjectRelationship(projectId, employeeId, teamMemberStatus));
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


    @GetMapping(value = "/projectsByEmployeeId/{employeeId}", produces = "application/json")
    public ResponseEntity<List<ProjectEmployee>> getProjectRelationshipsByEmployeeId(@PathVariable UUID employeeId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(projectService.getProjectRelationshipsByEmployeeId(employeeId));
    }

    @PostMapping("/projects-employees")
    public ResponseEntity<String> addTitleToProjectEmployee(@RequestBody Map<String, Object> requestBody) {
        UUID projectId = UUID.fromString((String) requestBody.get("projectId"));
        UUID employeeId = UUID.fromString((String) requestBody.get("employeeId"));
        UUID titleId = UUID.fromString((String) requestBody.get("titleId"));
        String teamMemberStatus = String.valueOf(requestBody.get("teamMemberStatus"));
        int rowsAffected = projectService.addProjectEmployeesTitle(projectId, employeeId, titleId, teamMemberStatus);
        if (rowsAffected == 1) {
            return ResponseEntity.ok("Title added successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to add title");
        }
    }

//
//        @Autowired
//        private EmployeeProjectTitleService employeeProjectTitleService;
//        @PostMapping("/{projectId}/employees/{employeeId}")
//        public ResponseEntity<?> addResponsibility(@PathVariable Long projectId, @PathVariable Long employeeId, @RequestBody String ) {
//            employeeProjectTitleService.addResponsibility(projectId, employeeId, responsibility);
//            return ResponseEntity.ok().build();
//        }
}
