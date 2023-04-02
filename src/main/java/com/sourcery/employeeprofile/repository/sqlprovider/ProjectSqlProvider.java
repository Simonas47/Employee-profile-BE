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
                .SELECT("*")
                .FROM("projects")
                .WHERE("projects.id = #{id}");
        return sql.toString();
    }

    public static String getAllProjects() {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("projects")
                .ORDER_BY("projects.startDate ASC");
        return sql.toString();
    }

    public static String createNewProjectRelationship() {
        SQL sql = new SQL()
                .INSERT_INTO("relationships")
                .VALUES("projectId", "#{projectId}")
                .VALUES("employeeId", "#{employeeId}");
        return sql.toString();
    }

    public static String getProjectRelationshipsByProjectId(@Param("projectId") UUID projectId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("relationships")
                .WHERE("relationships.projectId = #{projectId}");
        return sql.toString();
    }

    public static String getProjectRelationshipsByEmployeeId(@Param("employeeId") UUID employeeId) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("relationships")
                .WHERE("relationships.employeeId = #{employeeId}");
        return sql.toString();
    }
}
