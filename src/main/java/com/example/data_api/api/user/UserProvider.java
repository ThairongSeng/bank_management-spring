package com.example.data_api.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {

    public String buildInsertSql(@Param("u") User user){
        return new SQL(){{
            INSERT_INTO("users");
            VALUES("name", "#{u.name}");
            VALUES("gender", "#{u.gender}");
            VALUES("one_signal_id", "#{u.oneSignalId}");

            if(!user.getStudentCardId().isBlank()){
                VALUES("student_card_id", "#{u.studentCardId}");
            }

            VALUES("is_student", "#{u.isStudent}");
            VALUES("is_deleted", "FALSE");

        }}.toString();
    }


    public String buildSelectByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("id = #{id} AND is_deleted = FALSE");
        }}.toString();
    }

    public String buildSelectByCardIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM("users");
            WHERE("student_card_id = #{studentCardId} AND is_deleted = FALSE");
        }}.toString();
    }

    public String buildDeleteByIdSql(){
        return new SQL(){{
            DELETE_FROM("users");
            WHERE("id = #{id}");
        }}.toString();
    }


    public String buildUpdateIsDeleteByIdSql(){
        return new SQL(){{
            UPDATE("users");
            SET("is_deleted = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("is_deleted = FALSE", "name ilike '%' || #{name} || '%'");
            ORDER_BY("id DESC");
        }}.toString();
    }





    public String buildUpdateByIdSql(){
        return new SQL(){{
            UPDATE("users");
            SET("name = #{u.name}");
            SET("gender = #{u.gender}");
            WHERE("id = #{u.id}");
        }}.toString();
    }

}
