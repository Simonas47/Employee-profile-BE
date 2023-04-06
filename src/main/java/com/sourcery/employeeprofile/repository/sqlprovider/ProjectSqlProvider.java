package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class ProjectSqlProvider implements ProviderMethodResolver {
    public static String createNewProject() {
        SQL sql = new SQL()
                .INSERT_INTO("projects")
                .VALUES("title", "#{title}")
                .VALUES("startDate", "#{startDate}")
                .VALUES("endDate", "#{endDate}")
                .VALUES("description", "#{description}");
        return sql.toString();
    }

    public static String getProjectById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .SELECT("p.id", "p.title", "p.startDate", "p.endDate", "p.description")
                .FROM("projects p")
                .WHERE("p.id = #{id}");
        return sql.toString();
    }

    public static String getAllProjects() {
        SQL sql = new SQL()
                .SELECT("p.id", "p.title", "p.startDate", "p.endDate", "p.description")
                .FROM("projects p")
                .ORDER_BY("p.startDate ASC");
        return sql.toString();
    }

    public static String createNewProjectRelationship() {
        SQL sql = new SQL()
                .INSERT_INTO("projects_employees")
                .VALUES("projectId", "#{projectId}")
                .VALUES("employeeId", "#{employeeId}");
        return sql.toString();
    }

    public static String getProjectRelationshipsByProjectId(@Param("projectId") UUID projectId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("projects_employees")
                .WHERE("projects_employees.projectId = #{projectId}");
        return sql.toString();
    }
}
