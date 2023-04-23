package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.repository.sqlprovider.EmploymentDateSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmploymentDateRepository {
    @SelectProvider(type = EmploymentDateSqlProvider.class, method = "getEmploymentDates")
    List<EmploymentDate> getEmploymentDates(@Param("employeeId") int employeeId);

    @InsertProvider(type = EmploymentDateSqlProvider.class, method = "setEmploymentDates")
    void setEmploymentDates(@Param("employeeId") int employeeId,
                            @Param("employmentDates") List<EmploymentDate> employmentDates);
}
