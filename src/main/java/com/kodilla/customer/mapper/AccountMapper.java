package com.kodilla.customer.mapper;

import com.kodilla.customer.dto.Account;
import com.kodilla.customer.dto.AccountDto;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {
    public AccountDto mapToAccountDto(final Account account) {
        return new AccountDto(
                account.getId(),
                account.getNrb(),
                account.getCurrency(),
                account.getAvailableFunds()
        );
    }
}
