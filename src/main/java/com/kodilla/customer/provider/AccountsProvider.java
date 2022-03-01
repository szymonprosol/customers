package com.kodilla.customer.provider;

import com.kodilla.customer.connector.AccountsConnector;
import com.kodilla.customer.dto.Account;
import com.kodilla.customer.dto.AccountDto;
import com.kodilla.customer.mapper.AccountMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountsProvider {
    private final AccountsConnector accountsConnector;
    private final AccountMapper accountMapper;

    @HystrixCommand(fallbackMethod = "fallbackGetAccounts")
    public List<AccountDto> getCustomerAccounts(Long customerId) {
        return accountsConnector.getAccounts(customerId)
                .stream()
                .map(account -> accountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    private List<AccountDto> fallbackGetAccounts(Long customerId) {
        log.warn("Can not get accounts for customerId: {}", customerId);
        return Collections.emptyList();
    }

}