package com.example.data_api.api.user;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {


    @InsertProvider(type = UserProvider.class, method = "buildInsertSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);


    @SelectProvider(type = UserProvider.class, method = "buildSelectByIdSql")
    @Results(id = "userResultMap", value = {
            @Result(column = "is_student", property = "isStudent"),
            @Result(column = "student_card_id", property = "studentCardId")
    })
    Optional<User> selectById(@Param("id") Integer id);



    @SelectProvider(type = UserProvider.class, method = "buildSelectByCardIdSql")
    @ResultMap("userResultMap")
    Optional<User> selectByStudentCardId(@Param("studentCardId") String studentCardId);


    @Select("SELECT EXISTS(SELECT * FROM users WHERE id = #{id})")
    boolean existById(@Param("id") Integer id);



    @DeleteProvider(type = UserProvider.class, method = "buildDeleteByIdSql")
    void deleteById(@Param("id") Integer id);


    @UpdateProvider(type = UserProvider.class, method = "buildUpdateIsDeleteByIdSql")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);


    @SelectProvider(type = UserProvider.class, method = "buildSelectSql")
    @ResultMap("userResultMap")
    List<User> selectAllUsers(@Param("name") String name);



    @UpdateProvider(type = UserProvider.class, method = "buildUpdateByIdSql")
    void updateById(@Param("u") User user);

}
