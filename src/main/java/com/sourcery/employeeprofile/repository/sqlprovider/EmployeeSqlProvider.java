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
                .VALUES("hiringDate", "#{hiringDate}")
                .VALUES("exitDate", "#{exitDate}")
                .VALUES("titleId", "#{titleId}")
                .VALUES("imageId", "#{imageId}")
                .VALUES("status", "#{status}");
        return sql.toString();
    }

    public static String getById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate", "e.status",
                        "t.title",
                        "i.name as imageName", "i.type as imageType", "i.bytes as imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t on e.titleId = t.id", "images i on e.imageId = i.id")
                .WHERE("e.id = #{id}");
        return sql.toString();
    }

    public static String getEmployees(@Param("name") String name, @Param("page") Integer page, @Param("pageSize") Integer pageSize ) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate", "e.status",
                        "t.title",
                        "i.name as imageName", "i.type as imageType", "i.bytes as imageBytes")
                .FROM("employees e")
                .WHERE("LOWER(e.name) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.surname) LIKE LOWER(#{name})").OR()
                .WHERE("LOWER(e.middleName) LIKE LOWER(#{name})")
                .LEFT_OUTER_JOIN("titles t on e.titleId = t.id", "images i on e.imageId = i.id")
                .ORDER_BY("e.name ASC")
                .LIMIT("#{pageSize }")
                .OFFSET("#{page} * #{pageSize} - #{pageSize}");

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
}
