package com.sourcery.employeeprofile.repository.sqlprovider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class TitleSqlProvider implements ProviderMethodResolver {
    public static String getAllTitles() {
        SQL sql = new SQL()
                .SELECT("id", "title")
                .FROM("titles");
        return sql.toString();
    }
}
