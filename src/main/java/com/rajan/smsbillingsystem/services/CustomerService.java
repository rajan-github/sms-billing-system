package com.rajan.smsbillingsystem.services;

import com.rajan.smsbillingsystem.models.Customer;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public Optional<Customer> getCustomerById(long id);

    public List<Customer> getAllCustomers();

    public double sendMessage(final Customer customer, String messageContent, List<String> messageRecipients);

    public double getCostForMessages(final GregorianCalendar calendar, final Customer customer);
}
