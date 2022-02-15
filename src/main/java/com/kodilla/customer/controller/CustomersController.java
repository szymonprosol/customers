package com.kodilla.customer.controller;

import com.kodilla.customer.dto.CustomerDto;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomersController {

    private final DbService service;
    private final CustomerMapper customerMapper;

    @Value("${application.allow-get-customer}")
    private boolean allowGetCustomers;

    @GetMapping(value = "/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Long customerId) throws CustomerNotFoundException {
        if(!allowGetCustomers) {
            log.info("Getting customers is disabled");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting customers is disabled");
        }
        return customerMapper.mapToCustomerDto(
                service.getCustomerById(customerId).orElseThrow(CustomerNotFoundException::new)
        );
    }
}