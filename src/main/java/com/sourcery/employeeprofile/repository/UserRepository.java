package com.sourcery.employeeprofile.repository;

import com.sourcery.employeeprofile.model.User;
import com.sourcery.employeeprofile.repository.sqlprovider.UsersSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Mapper
public interface UserRepository {
    @InsertProvider(type = UsersSqlProvider.class, method = "createNewUser")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void createNewUser(User user);

    @SelectProvider(type = UsersSqlProvider.class, method = "getByEmailAndPassword")
    Optional<User> getByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
