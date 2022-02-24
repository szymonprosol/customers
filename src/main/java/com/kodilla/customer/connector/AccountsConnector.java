package com.kodilla.customer.connector;

import com.kodilla.customer.dto.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "accounts")
public interface AccountsConnector {

    @GetMapping("/v1/accounts")
    List<Account> getAccounts(@RequestParam("customerId") Long customerId);
}
