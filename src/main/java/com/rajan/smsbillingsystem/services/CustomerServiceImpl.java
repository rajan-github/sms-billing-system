package com.rajan.smsbillingsystem.services;

import com.rajan.smsbillingsystem.models.Customer;
import com.rajan.smsbillingsystem.models.SentMessages;
import com.rajan.smsbillingsystem.repository.CustomerRepository;
import com.rajan.smsbillingsystem.repository.SentMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SentMessageRepository sentMessageRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, SentMessageRepository sentMessageRepository) {
        this.customerRepository = customerRepository;
        this.sentMessageRepository = sentMessageRepository;
    }

    @Override
    public Optional<Customer> getCustomerById(long id) {
        logger.info("Fetching customer for Id: {}", id);
        final Customer customer = customerRepository.findById(id);
        if (customer == null) {
            logger.info("Customer not found for Id: {}", id);
            return Optional.empty();
        }
        logger.info("Fetched customer {} for id and returning it.", customer);
        return Optional.of(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.info("Fetching all the customers from db.");
        final List<Customer> customers = customerRepository.findAll();
        if (customers == null || customers.isEmpty()) {
            return List.of();
        }
        return customers;
    }

    @Override
    public double sendMessage(final Customer customer, final String messageContent, final List<String> messageRecipients) {
        logger.info("Sending messages for customer: {}", customer);
        for (String recipient : messageRecipients) {
            sentMessageRepository.save(new SentMessages(customer, recipient, new Date(), messageContent));
        }
        return getCostForMessages(new GregorianCalendar(), customer);
    }

    @Override
    public double getCostForMessages(final GregorianCalendar calendar, final Customer customer) {
        var planType = customer.getPlanType();
        var startDate = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH), 0).getTime();
        var endDate = new GregorianCalendar(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH), calendar.getMaximum(GregorianCalendar.DAY_OF_MONTH)).getTime();
        List<SentMessages> messages = sentMessageRepository.getSentMessagesByMonth(startDate, endDate, customer.getId());
        if (planType.getFreeMsg() >= messages.size()) {
            return 0.0;
        }
        return (messages.size() - planType.getFreeMsg()) * planType.getRate();
    }
}
