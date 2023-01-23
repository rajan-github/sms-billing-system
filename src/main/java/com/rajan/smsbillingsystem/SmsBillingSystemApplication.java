package com.rajan.smsbillingsystem;

import com.rajan.smsbillingsystem.models.Customer;
import com.rajan.smsbillingsystem.models.PlanType;
import com.rajan.smsbillingsystem.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmsBillingSystemApplication {
    private static final Logger logger = LoggerFactory.getLogger(SmsBillingSystemApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SmsBillingSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(CustomerRepository customerRepository) {
        return (args) -> {
            logger.info("Loading customers to the db.");
            customerRepository.save(new Customer("Bank", PlanType.GOLD));
            customerRepository.save(new Customer("Shop", PlanType.SILVER));
            customerRepository.save(new Customer("Kp", PlanType.BASIC));
        };
    }
}
