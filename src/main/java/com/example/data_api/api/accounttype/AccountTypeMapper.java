package com.example.data_api.api.accounttype;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AccountTypeMapper {

    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectSql")
    List<AccountType> select();



    @SelectProvider(type = AccountTypeProvider.class, method = "buildSelectByIdSql")
    Optional<AccountType> selectById(@Param("id") Integer id);



    @InsertProvider(type = AccountTypeProvider.class, method = "buildInsertSql")
    @Options(keyProperty = "id", keyColumn = "id")
    void insert(@Param("ac") AccountType accountType);



    @Select("SELECT EXISTS(SELECT * FROM account_types WHERE id = #{id})")
    boolean isExist(@Param("id") Integer id);


    @UpdateProvider(type = AccountTypeProvider.class, method = "buildUpdateByIdSql")
    void update(@Param("ac") AccountType accountType);



    @DeleteProvider(type = AccountTypeProvider.class, method = "buildDeleteByIdSql")
    Integer delete(@Param("id") Integer id);

}
