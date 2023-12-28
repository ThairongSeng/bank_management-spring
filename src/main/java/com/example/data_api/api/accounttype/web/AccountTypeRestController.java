package com.example.data_api.api.accounttype.web;


import com.example.data_api.api.accounttype.AccountTypeService;
import com.example.data_api.api.accounttype.web.AccountTypeDto;
import com.example.data_api.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeRestController {


    private final AccountTypeService accountTypeService;

    @GetMapping
    public BaseRest<?> findAll(){

        var accountType = accountTypeService.findAll();

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been found successfully..!")
                .timestamp(LocalDateTime.now())
                .data(accountType)
                .build();
    }


    @GetMapping("/{id}")
    public BaseRest<?> findById(@PathVariable Integer id){

        var accountTypeById = accountTypeService.findById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been found successfully..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeById)
                .build();
    }


    @PostMapping
    public BaseRest<?> createNew(@RequestBody @Valid AccountTypeDto accountBody){

        AccountTypeDto accountTypeDto = accountTypeService.createAccountType(accountBody);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been created successfully..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }


    @PutMapping("/{id}")
    public BaseRest<?> updateById(@PathVariable Integer id, @RequestBody @Valid  AccountTypeDto accountTypeDto){

        AccountTypeDto accountTypeDto1 = accountTypeService.updateAccount(id, accountTypeDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been updated successfully..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto1)
                .build();
    }


    @DeleteMapping("/{id}")
    public BaseRest<?> deleteById(@PathVariable Integer id){
        Integer accountTypeDto = accountTypeService.deleteAccount(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Account type has been deleted successfully..!")
                .timestamp(LocalDateTime.now())
                .data(accountTypeDto)
                .build();
    }
}
