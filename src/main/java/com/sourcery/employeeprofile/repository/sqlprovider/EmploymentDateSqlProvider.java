package com.sourcery.employeeprofile.repository.sqlprovider;

import com.sourcery.employeeprofile.model.EmploymentDate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class EmploymentDateSqlProvider implements ProviderMethodResolver {
    public static String getEmploymentDates(@Param("employeeId") Integer employeeId) {
        SQL sql = new SQL()
                .SELECT("hiringDate", "exitDate")
                .FROM("employment_dates")
                .WHERE("employeeId = #{employeeId}")
                .ORDER_BY("hiringDate ASC");
        return sql.toString();
    }

    public static String setEmploymentDates(@Param("employeeId") Integer employeeId,
                                            @Param("employmentDates") List<EmploymentDate> employmentDates) {
        return "<script>" +
                "INSERT INTO employment_dates" +
                "(employeeId, hiringDate, exitDate)" +
                "VALUES" +
                "<foreach item='date' collection='employmentDates' open='(' separator='),(' close=')'>" +
                "#{employeeId}, #{date.hiringDate}, #{date.exitDate}" +
                "</foreach>" +
                "</script>";
    }
}

