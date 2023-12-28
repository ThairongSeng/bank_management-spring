package com.example.data_api.api.user.web;

import com.example.data_api.api.user.UserService;
import com.example.data_api.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;


    @PutMapping("/{id}")
    public BaseRest<?> updateUserById(@PathVariable("id") Integer id, @RequestBody UpdateUserDto updateUserDto){

        UserDto userDto = userService.updateUserById(id, updateUserDto);

        return BaseRest.builder()
                .status(true)
                .message("User has been updated successfully..!")
                .code(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }


    @GetMapping
    public BaseRest<?> findAllUsers(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(name = "limit", required = false, defaultValue = "20") int limit,
                                    @RequestParam(name = "name", required = false, defaultValue = "") String name){

        PageInfo<UserDto> userDtoPageInfo = userService.findAllUsers(page,limit, name);

        return BaseRest.builder()
                .status(true)
                .message("User has been found successfully..!")
                .code(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(userDtoPageInfo)
                .build();
    }


    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid CreateUserDto createUserDto) {
        UserDto userDto = userService.createNewUser(createUserDto);

        return BaseRest.builder()
                .status(true)
                .message("User has been created successfully..!")
                .code(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }


    @GetMapping("/{identifier}")
    public BaseRest<?> findUserByIdentifier(@PathVariable("identifier") String identifier) {

        UserDto userDto;

      try {
          Integer id = Integer.parseInt(identifier);
          userDto = userService.findUserById(id);
      }catch (NumberFormatException e){
          userDto = userService.findUserByStudentCardId(identifier);
      }

        return BaseRest.builder()
                .status(true)
                .message("User has been found successfully..!")
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }


    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUserById(@PathVariable Integer id) {
        Integer deletedId = userService.deleteUserById(id);

        return BaseRest.builder()
                .status(true)
                .message("User has been deleted successfully..!")
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }


    @PutMapping("/{id}/is-deleted")
    public BaseRest<?> updateIsDeletedStatusById(@PathVariable Integer id, @RequestBody  IsDeletedDto dto) {
        Integer deletedId = userService.updateIsDeletedStatus(id, dto.status());

        return BaseRest.builder()
                .status(true)
                .message("User has been deleted successfully..!")
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(deletedId)
                .build();
    }
}
