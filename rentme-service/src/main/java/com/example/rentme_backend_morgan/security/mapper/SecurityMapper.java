package com.example.rentme_backend_morgan.security.mapper;


import com.example.rentme_backend_morgan.security.entities.Account;
import com.example.rentme_backend_morgan.security.dto.AccountDto;

import java.util.List;
import java.util.stream.Collectors;

public class SecurityMapper {

    public static AccountDto accountDto(Account account) {
        return new AccountDto(account.getAccId(), account.getRole());
//                stream()
//                .map(role -> role.substring(5))
//                .collect(Collectors.toSet()));
    }

    public static List<AccountDto> accountDtoList(List<Account> accountList) {
        return accountList.stream()
                .map(account -> accountDto(account))
                .collect(Collectors.toList());
    }
}

