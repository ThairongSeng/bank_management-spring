package com.example.data_api.api.accounttype;

import com.example.data_api.api.accounttype.web.AccountTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{


    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;


    @Override
    public List<AccountTypeDto> findAll() {
        List<AccountType> accountTypeList = accountTypeMapper.select();

        return accountTypeMapStruct.toListAccountTypeDto(accountTypeList);
    }

    @Override
    public AccountTypeDto findById(Integer id) {
        AccountType accountType = accountTypeMapper.selectById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Account type with id = %d is not found", id)
                ));
        return accountTypeMapStruct.toAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto createAccountType(AccountTypeDto accountTypeDto) {
        AccountType accountType = accountTypeMapStruct.fromAccountTypeDto(accountTypeDto);
        accountTypeMapper.insert(accountType);

        return accountTypeMapStruct.toAccountTypeDto(accountType);
    }

    @Override
    public AccountTypeDto updateAccount(Integer id, AccountTypeDto accountTypeDto) {
        if(accountTypeMapper.isExist(id)){
            AccountType accountType = accountTypeMapStruct.fromAccountTypeDto(accountTypeDto);
            accountType.setId(id);
            accountTypeMapper.update(accountType);
            return findById(id);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Account Type with id = %d is not found", id)
        );

    }

    @Override
    public Integer deleteAccount(Integer id) {
        boolean isExisted = accountTypeMapper.isExist(id);
        if(isExisted){
            accountTypeMapper.delete(id);
            return id;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("User with id = %d is not found", id)
        );
    }
}
