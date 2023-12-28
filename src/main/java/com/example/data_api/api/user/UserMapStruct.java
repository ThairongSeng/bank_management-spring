package com.example.data_api.api.user;

import com.example.data_api.api.user.web.CreateUserDto;
import com.example.data_api.api.user.web.UpdateUserDto;
import com.example.data_api.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {


    User createUserDtoToUser(CreateUserDto createUserDto);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);

    PageInfo<UserDto> userPageInfoToUserDtoPageInfo(PageInfo<User> userPageInfo);

    User updateUserDtoToUser(UpdateUserDto updateUserDto);

}
