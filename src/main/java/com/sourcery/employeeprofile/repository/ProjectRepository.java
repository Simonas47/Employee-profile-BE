package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.Project;
import com.sourcery.employeeprofile.model.ProjectEmployee;
import com.sourcery.employeeprofile.repository.sqlprovider.ProjectSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Mapper
public interface ProjectRepository {
    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProject")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewProject(Project project);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectById")
    Project getProjectById(@Param("id") UUID id);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getAllProjects")
    List<Project> getAllProjects();

    @InsertProvider(type = ProjectSqlProvider.class, method = "createNewProjectRelationship")
    void createNewProjectRelationship(UUID projectId, UUID employeeId);

    @SelectProvider(type = ProjectSqlProvider.class, method = "getProjectRelationshipsByProjectId")
    List<ProjectEmployee> getProjectRelationshipsByProjectId(@Param("projectId") UUID projectId);
}
