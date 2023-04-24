package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ImageSqlProvider implements ProviderMethodResolver {
    public static String createNewImage() {
        SQL sql = new SQL()
                .INSERT_INTO("images")
                .VALUES("name", "#{name}")
                .VALUES("type", "#{type}")
                .VALUES("bytes", "#{bytes}");
        return sql.toString();
    }

    public static String getById(@Param("id") Integer id) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("images")
                .WHERE("id = #{id}");
        return sql.toString();
    }
}
