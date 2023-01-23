package com.rajan.smsbillingsystem.controller;

import com.rajan.smsbillingsystem.exceptions.BadRequestException;
import com.rajan.smsbillingsystem.exceptions.NotFoundException;
import com.rajan.smsbillingsystem.models.Customer;
import com.rajan.smsbillingsystem.models.SendMessageRequest;
import com.rajan.smsbillingsystem.models.SendMessageResponse;
import com.rajan.smsbillingsystem.services.CustomerService;
import com.rajan.smsbillingsystem.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = "/customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        final Optional<Customer> customerOptional = customerService.getCustomerById(id);
        var customer = customerOptional.orElseThrow(() -> new NotFoundException(String.format("Customer for id: %d doesn't exist.", id)));
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/sendMessage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody final SendMessageRequest messageRequest) {
        if (messageRequest == null || messageRequest.getMessageContent() == null || messageRequest.getMessageRecipients() == null || messageRequest.getMessageRecipients().isEmpty() || messageRequest.getCustomerId() == null) {
            throw new BadRequestException("Invalid request body.");
        }
        final Optional<Customer> customerOptional = customerService.getCustomerById(messageRequest.getCustomerId());
        final var customer = customerOptional.orElseThrow(() -> new NotFoundException(String.format("Invalid customer id: %d", messageRequest.getCustomerId())));
        var cost = customerService.sendMessage(customer, messageRequest.getMessageContent(), messageRequest.getMessageRecipients());
        return new ResponseEntity<>(SendMessageResponse.builder().messageRecipients(messageRequest.getMessageRecipients()).cost(Utilities.formatMoneyToUSDollar(cost)).planType(customer.getPlanType()).build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/computeCost/{month}/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCostForTheMonth(@PathVariable long customerId, @PathVariable int month, @PathVariable int year) {
        var calender = Calendar.getInstance();
        if (calender.get(Calendar.YEAR) < year || (calender.get(Calendar.YEAR) == year && month > calender.get(Calendar.MONTH))) {
            throw new BadRequestException("Please pass the month and year from past or current");
        }
        final Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
        final var customer = customerOptional.orElseThrow(() -> new NotFoundException(String.format("Invalid customer id: %d", customerId)));
        var newCalender = new GregorianCalendar();
        newCalender.set(GregorianCalendar.YEAR, year);
        newCalender.set(GregorianCalendar.MONTH, month);
        newCalender.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return new ResponseEntity<>(Utilities.formatMoneyToUSDollar(customerService.getCostForMessages(newCalender, customer)), HttpStatus.OK);
    }
}
