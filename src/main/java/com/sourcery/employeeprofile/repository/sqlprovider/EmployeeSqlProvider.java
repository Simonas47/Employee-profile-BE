package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class EmployeeSqlProvider implements ProviderMethodResolver {
    static final int DEFAULT_LIMIT = 100;

    public static String createNewEmployee() {
        SQL sql = new SQL()
                .INSERT_INTO("employees")
                .VALUES("name", "#{name}")
                .VALUES("surname", "#{surname}")
                .VALUES("middleName", "#{middleName}")
                .VALUES("hiringDate", "#{hiringDate}")
                .VALUES("exitDate", "#{exitDate}")
                .VALUES("titleId", "#{titleId}")
                .VALUES("imageId", "#{imageId}");
        return sql.toString();
    }

    public static String getById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate",
                        "t.title",
                        "i.name AS imageName", "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .WHERE("e.id = #{id}");
        return sql.toString();
    }

    public static String getAllByNameLike(@Param("name") String name,
                                          @Param("limit") Integer limit) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate",
                        "t.title",
                        "i.name AS imageName", "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .WHERE("LOWER(e.name) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.surname) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.middleName) LIKE LOWER(#{name})")
                .ORDER_BY("e.name ASC");
        if (limit != null) {
            sql.LIMIT("#{limit}");
        } else {
            sql.LIMIT(DEFAULT_LIMIT);
        }
        return sql.toString();
    }

    public static String getEmployeesByProjectId(@Param("projectId") UUID projectId) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate",
                        "t.title",
                        "i.name AS imageName", "i.type AS imageType", "i.bytes AS imageBytes")
                .FROM("projects_employees pe")
                .INNER_JOIN("employees e ON pe.employeeId = e.id")
                .LEFT_OUTER_JOIN("titles t ON e.titleId = t.id",
                        "images i ON e.imageId = i.id")
                .WHERE("pe.projectId = #{projectId}")
                .ORDER_BY("e.name ASC");
        return sql.toString();
    }
}
