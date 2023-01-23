package com.rajan.smsbillingsystem.repository;

import com.rajan.smsbillingsystem.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    public Customer findById(long id);

    public List<Customer> findAll();
}
