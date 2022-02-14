package com.kodilla.customer.mapper;

import com.kodilla.customer.dto.Customer;
import com.kodilla.customer.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public CustomerDto mapToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname()
        );
    }
}
