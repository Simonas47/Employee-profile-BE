package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class EmployeeSqlProvider implements ProviderMethodResolver {
    static final int DEFAULT_LIMIT = 100;

    public static String getAllByNameLike(@Param("name") String name,
                                          @Param("limit") Integer limit) {
        SQL sql = new SQL()
                .SELECT("e.id", "e.name", "e.surname", "e.middleName", "e.hiringDate", "e.exitDate",
                        "t.title",
                        "i.name as imageName", "i.type as imageType", "i.bytes as imageBytes")
                .FROM("employees e")
                .LEFT_OUTER_JOIN("titles t on e.titleId = t.id", "images i on e.imageId = i.id");
        if (name != null) {
            sql.WHERE("LOWER(e.name) LIKE #{name} OR " +
                    "LOWER(e.surname) LIKE #{name} OR " +
                    "LOWER(e.middleName) LIKE #{name}");
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
