package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class UsersSqlProvider implements ProviderMethodResolver {

    public static String createNewUser() {
        SQL sql = new SQL()
                .INSERT_INTO("users")
                .VALUES("email", "#{email}")
                .VALUES("password", "#{password}");
        return sql.toString();
    }

    public static String getByEmailAndPassword(@Param("email") String email, @Param("password") String password) {
        SQL sql = new SQL()
                .SELECT("u.id", "u.email", "u.password")
                .FROM("users u")
                .WHERE("u.email = #{email} AND u.password = #{password}");
        return sql.toString();
    }
}