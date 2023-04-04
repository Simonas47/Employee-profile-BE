package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.UUID;

public class UsersSqlProvider implements ProviderMethodResolver {


    public static String createNewUser() {
        SQL sql = new SQL()
                .INSERT_INTO("users")
                .VALUES("email", "#{email}")
                .VALUES("password", "#{password}");
        return sql.toString();
    }

    public static String getById(@Param("id") UUID id) {
        SQL sql = new SQL()
                .SELECT("u.id", "u.email", "u.password")
                .FROM("users u")
                .WHERE("u.id = #{id}");
        return sql.toString();
    }
    public static String getByEmail(@Param("email") String email) {
        SQL sql = new SQL()
                .SELECT("u.id", "u.email", "u.password")
                .FROM("users u")
                .WHERE("u.email = #{email}");
        return sql.toString();
    }

    public static String getByEmailAndPassword(@Param("email") String email, @Param("password")String password) {
        SQL sql = new SQL()
                .SELECT( "u.id", "u.email", "u.password")
                .FROM("users u")
                .WHERE("u.email = #{email} AND u.password = #{password}");
        return sql.toString();
    }

    public static String getUsers(@Param("email") String email, @Param("page") Integer page, @Param("pageSize") Integer pageSize ) {
        SQL sql = new SQL()
                .SELECT("u.id", "u.email", "u.password")
                .FROM("users u")
                .WHERE("LOWER(u.email) LIKE LOWER(#{email})").OR()
                .WHERE("LOWER(u.password) LIKE LOWER(#{email})")
                .ORDER_BY("u.email ASC")
                .LIMIT("#{pageSize }")
                .OFFSET("#{page} * #{pageSize} - #{pageSize}");
        return sql.toString();
    }

    public static String getUsersCountByEmail(@Param("email") String email) {
        SQL sql = new SQL()
                .SELECT("COUNT(1)")
                .FROM("users u")
                .WHERE("LOWER(u.email) LIKE LOWER(#{email})").OR()
                .WHERE("LOWER(u.email) LIKE LOWER(#{email})");

        return sql.toString();
    }
}