package com.example.data_api.api.accounttype;

import com.example.data_api.api.accounttype.web.AccountTypeDto;

import java.util.List;

public interface AccountTypeService {

    List<AccountTypeDto> findAll();


    AccountTypeDto findById(Integer id);


    AccountTypeDto createAccountType(AccountTypeDto accountTypeDto);


    AccountTypeDto updateAccount(Integer id, AccountTypeDto accountTypeDto);

    Integer deleteAccount(Integer id);

}
