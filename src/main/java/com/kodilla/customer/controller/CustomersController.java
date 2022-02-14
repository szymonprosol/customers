package com.kodilla.customer.controller;

import com.kodilla.customer.dto.CustomerDto;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomersController {

    private final DbService service;
    private final CustomerMapper customerMapper;

    @GetMapping(value = "/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Long customerId) throws CustomerNotFoundException {
        return customerMapper.mapToCustomerDto(
                service.getCustomerById(customerId).orElseThrow(CustomerNotFoundException::new)
        );
    }
}