package com.example.data_api.api.accounttype;


import com.example.data_api.api.accounttype.web.AccountTypeDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {


    List<AccountTypeDto> toListAccountTypeDto(List<AccountType> accountTypes);

    AccountTypeDto toAccountTypeDto(AccountType accountType);

    AccountType fromAccountTypeDto(AccountTypeDto accountTypeDto);
}
