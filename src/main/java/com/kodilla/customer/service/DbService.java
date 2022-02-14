package com.kodilla.customer.service;

import com.kodilla.customer.dto.Customer;
import com.kodilla.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final CustomerRepository repository;

    public Optional<Customer> getCustomerById(final Long customerId) {
        return repository.findById(customerId);
    }
}