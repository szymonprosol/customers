package com.kodilla.customer.provider;

import com.kodilla.customer.connector.AccountsConnector;
import com.kodilla.customer.dto.AccountDto;
import com.kodilla.customer.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountsProvider {
    private final AccountsConnector accountsConnector;
    private final AccountMapper accountMapper;

    public List<AccountDto> getCustomerAccounts(Long customerId) {
        return accountsConnector.getAccounts(customerId)
                .stream()
                .map(account -> accountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

}