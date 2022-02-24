package com.kodilla.customer.controller;

import com.kodilla.customer.dto.AccountDto;
import com.kodilla.customer.dto.Customer;
import com.kodilla.customer.dto.CustomerDto;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.response.GetCustomerProductsResponse;
import com.kodilla.customer.service.DbService;
import com.kodilla.customer.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RefreshScope
@RequestMapping(value = "/v1/customers", produces = { MediaType.APPLICATION_JSON_VALUE })
@RequiredArgsConstructor
public class CustomersController {

    private final DbService service;
    private final CustomerMapper customerMapper;
    private final ProductService productService;

    @Value("${application.allow-get-customer}")
    private boolean allowGetCustomer;

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable Long customerId) throws CustomerNotFoundException {
        return customerMapper.mapToCustomerDto(
                service.getCustomerById(customerId).orElseThrow(CustomerNotFoundException::new)
        );
    }

    @GetMapping("/{customerId}/products")
    public GetCustomerProductsResponse getCustomerProducts(@PathVariable Long customerId) {
        Customer customer = service.getCustomerById(customerId).get();
        CustomerDto customerDto = customerMapper.mapToCustomerDto(customer);

        List<AccountDto> customerAccounts = productService.findCustomerAccounts(customerId);

        return GetCustomerProductsResponse.builder()
                .customerId(customerDto.getId())
                .fullName(customerDto.getFirstname() + " " + customerDto.getLastname())
                .accounts(customerAccounts)
                .build();
    }
}