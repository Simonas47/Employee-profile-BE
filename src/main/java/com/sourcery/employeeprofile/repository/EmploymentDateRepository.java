package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.dto.EmployeeDto;
import com.sourcery.employeeprofile.model.Employee;
import com.sourcery.employeeprofile.model.EmploymentDate;
import com.sourcery.employeeprofile.repository.sqlprovider.EmployeeSqlProvider;
import com.sourcery.employeeprofile.repository.sqlprovider.EmploymentDateSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@Mapper
public interface EmploymentDateRepository {
    @SelectProvider(type = EmploymentDateSqlProvider.class, method = "getEmploymentDates")
    List<EmploymentDate> getEmploymentDates(@Param("employeeId") UUID employeeId);

    @InsertProvider(type = EmploymentDateSqlProvider.class, method = "setEmploymentDates")
    void setEmploymentDates(@Param("employeeId") UUID employeeId, @Param("employmentDates") List<EmploymentDate> employmentDates);
}
