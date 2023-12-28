package com.example.data_api.api.user;

import com.example.data_api.api.user.web.CreateUserDto;
import com.example.data_api.api.user.web.UpdateUserDto;
import com.example.data_api.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

public interface UserService {

    UserDto createNewUser(CreateUserDto createUserDto);

    UserDto findUserById(Integer id);

    UserDto findUserByStudentCardId(String studentCardId);

    PageInfo<UserDto> findAllUsers(int page, int limit, String name);

    Integer deleteUserById(Integer id);

    Integer updateIsDeletedStatus(Integer id, boolean status);

    UserDto updateUserById(Integer id, UpdateUserDto updateUserDto);
}
