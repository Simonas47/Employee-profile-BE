package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class ImageSqlProvider implements ProviderMethodResolver {
    public static String createNewImage() {
        SQL sql = new SQL()
                .INSERT_INTO("images")
                .VALUES("id", "#{id}")
                .VALUES("name", "#{name}")
                .VALUES("type", "#{type}")
                .VALUES("bytes", "#{bytes}");
        return sql.toString();
    }

    public static String getById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .SELECT("*")
                .FROM("images")
                .WHERE("id = #{id}");
        return sql.toString();
    }
}
