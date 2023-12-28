package com.example.data_api.api.user;


import com.example.data_api.api.user.web.CreateUserDto;
import com.example.data_api.api.user.web.UpdateUserDto;
import com.example.data_api.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;


    //create
    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.createUserDtoToUser(createUserDto);
        userMapper.insert(user);

//        UserDto userDto = userMapStruct.userToUserDto(user);
//        return userDto;
        log.info("User = {}", user.getId());
//        return userMapStruct.userToUserDto(user);
        return this.findUserById(user.getId());
    }


    //get by id
    @Override
    public UserDto findUserById(Integer id) {
        User user = userMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with id = %d is not found", id)
                ));

        return userMapStruct.userToUserDto(user);
    }

    @Override
    public UserDto findUserByStudentCardId(String studentCardId) {
        User user = userMapper.selectByStudentCardId(studentCardId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("User with studentCardId = %s is not found", studentCardId)
                ));

        return userMapStruct.userToUserDto(user);
    }


    //get all user that have limit and page
    @Override
    public PageInfo<UserDto> findAllUsers(int page, int limit, String name) {

        PageInfo<User> userPageInfo = PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> userMapper.selectAllUsers(name));

        return userMapStruct.userPageInfoToUserDtoPageInfo(userPageInfo);
    }


    //delete by id
    @Override
    public Integer deleteUserById(Integer id) {
        boolean isExisted = userMapper.existById(id);
        if (isExisted){
            //DELETE
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with id = %d is not found", id)
        );
    }


    //change status delete false to true
    @Override
    public Integer updateIsDeletedStatus(Integer id, boolean status) {
        boolean isExisted = userMapper.existById(id);
        if (isExisted){
            userMapper.updateIsDeletedById(id, status);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with id = %d is not found", id)
        );
    }

    @Override
    public UserDto updateUserById(Integer id, UpdateUserDto updateUserDto) {
        if(userMapper.existById(id)){
            User user = userMapStruct.updateUserDtoToUser(updateUserDto);
            user.setId(id);
            userMapper.updateById(user);
            return this.findUserById(id);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with id = %d is not found", id)
        );
    }


}
