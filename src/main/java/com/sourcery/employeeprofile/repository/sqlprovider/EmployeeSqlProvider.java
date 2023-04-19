package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class EmployeeSqlProvider implements ProviderMethodResolver {
    public static String createNewEmployee() {
        SQL sql = new SQL()
                .INSERT_INTO("employees")
                .VALUES("name", "#{name}")
                .VALUES("surname", "#{surname}")
                .VALUES("middleName", "#{middleName}")
                .VALUES("titleId", "#{titleId}")
                .VALUES("imageId", "#{imageId}")
                .VALUES("status", "#{status}")
                .VALUES("isManager", "#{isManager}");
        return sql.toString();
    }

    public static String getById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.status", "e.isManager",
                        "t.title",
                        "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .WHERE("e.id = #{id}");
        return sql.toString();
    }

    public static String getEmployees(@Param("name") String name,
                                      @Param("page") Integer page,
                                      @Param("pageSize") Integer pageSize,
                                      @Param("isLimited") Boolean isLimited) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.status", "e.isManager",
                        "t.title",
                        "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("employees e")
                .WHERE("LOWER(e.name) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.surname) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.middleName) LIKE LOWER(#{name})")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .ORDER_BY("e.name ASC");

                if (isLimited) {
                        sql
                        .LIMIT("#{pageSize}")
                        .OFFSET("#{page} * #{pageSize} - #{pageSize}");
                }
        return sql.toString();
    }

    public static String getEmployeeCountByName(@Param("name") String name) {
        SQL sql = new SQL()
                .SELECT("COUNT(1)")
                .FROM("employees e")
                .WHERE("LOWER(e.name) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.surname) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.middleName) LIKE LOWER(#{name})");
        return sql.toString();
    }

    public static String getTeamMembersByProjectId(@Param("projectId") UUID projectId) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.isManager",
                        "t.title",
                        "i.name AS imageName", "i.type AS imageType", "i.bytes AS imageBytes",
                        "pe.teamMemberStatus AS status", "pe.teamMemberStartDate AS startDate", "pe.teamMemberEndDate AS endDate",
                        "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("projects_employees pe")
                .INNER_JOIN("employees e ON pe.employeeId = e.id")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .WHERE("pe.projectId = #{projectId}")
                .ORDER_BY("e.name ASC");
        return sql.toString();
    }
}
