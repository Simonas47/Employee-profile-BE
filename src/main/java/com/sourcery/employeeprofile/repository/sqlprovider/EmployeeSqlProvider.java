package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

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

    public static String getById(@Param("id") Integer id) {
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
                                      String searchBySkillIdSqlCode,
                                      String searchByAchievementIdSqlCode,
                                      @Param("page") Integer page,
                                      @Param("pageSize") Integer pageSize,
                                      @Param("isLimited") Boolean isLimited) {
        SQL sql = new SQL()
                .SELECT("e1.id", "e1.name", "e1.surname", "e1.middleName", "e1.status", "e1.isManager",
                        "t1.title",
                        "i1.type AS imageType", "i1.bytes AS imageBytes")
                .FROM("employees e1")
                .LEFT_OUTER_JOIN("titles t1 ON e1.titleId = t1.id",
                        "images i1 ON e1.imageId = i1.id")
                .WHERE("e1.id IN " +
                        "(SELECT e2.id FROM employees e2 " +
                        "WHERE LOWER(e2.name) LIKE LOWER(#{name}) OR " +
                        "LOWER(e2.surname) LIKE LOWER(#{name}) OR " +
                        "LOWER(e2.middleName) LIKE LOWER(#{name}))");
        if (!searchBySkillIdSqlCode.equals("")) {
            sql.WHERE(searchBySkillIdSqlCode);
        }
        if (!searchByAchievementIdSqlCode.equals("")) {
            sql.WHERE(searchByAchievementIdSqlCode);
        }
        sql.ORDER_BY("e1.name ASC, e1.surname ASC");
        if (isLimited) {
            sql
                    .LIMIT("#{pageSize}")
                    .OFFSET("#{page} * #{pageSize} - #{pageSize}");
        }
        return sql.toString();
    }

    public static String getProjectEmployeesByProjectId(@Param("projectId") Integer projectId) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName",
                        "t.title",
                        "i.type AS imageType", "i.bytes AS imageBytes",
                        "pe.projectEmployeeStatus", "pe.projectEmployeeStartDate", "pe.projectEmployeeEndDate",
                        "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("projects_employees pe")
                .INNER_JOIN("employees e ON pe.employeeId = e.id")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .WHERE("pe.projectId = #{projectId}")
                .ORDER_BY("e.name ASC, e.surname ASC");
        return sql.toString();
    }
}
