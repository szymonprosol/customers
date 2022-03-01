package com.kodilla.customer.connector;

import com.kodilla.customer.dto.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
class AccountsConnectorFallback implements AccountsConnector {

    @Override
    public List<Account> getAccounts(@RequestParam("customerId") Long customerId) {
        log.warn("Can not get accounts for customerId: {}", customerId);
        return Collections.emptyList();
    }
}
