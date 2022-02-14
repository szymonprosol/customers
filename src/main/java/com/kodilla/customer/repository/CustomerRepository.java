package com.kodilla.customer.repository;

import com.kodilla.customer.dto.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long id);
}
