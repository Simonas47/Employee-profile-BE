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
                        "i.name as imageName", "i.type as imageType", "i.bytes as imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t on e.titleId = t.id", "images i on e.imageId = i.id");
        if (id != null) {
            sql.WHERE("e.id = #{id}");
        }
        return sql.toString();
    }

    public static String getAllByNameLike(@Param("name") String name,
                                          @Param("limit") Integer limit) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate",
                        "t.title",
                        "i.name as imageName", "i.type as imageType", "i.bytes as imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t on e.titleId = t.id", "images i on e.imageId = i.id");
        if (name != null) {
            sql.WHERE("LOWER(e.name) LIKE #{name}").OR()
                    .WHERE("LOWER(e.surname) LIKE #{name}").OR()
                    .WHERE("LOWER(e.middleName) LIKE #{name}");
        }
        sql.ORDER_BY("e.name ASC");
        if (limit != null) {
            sql.LIMIT("#{limit}");
        } else {
            sql.LIMIT(DEFAULT_LIMIT);
        }
        return sql.toString();
    }
}
