package com.demo.asyncmicroservice.service;

import com.demo.asyncmicroservice.domain.Customer;
import com.demo.asyncmicroservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    public List<Customer> getCustomerByName(String name) {
        log.info("Getting customer {} from the repository.", name);

        List<Customer> customerList = customerRepository.findByName(name);

        if (CollectionUtils.isEmpty(customerList)) {
            log.info("No customer found by name {}", name);
            return new ArrayList<>();
        }
        log.info("found {} customers by name {}", customerList.size(), name);
        return customerList;
    }

    public Customer addCustomer(Customer customer) {
        log.info("Adding customer {} to database", customer.getName());
        Customer customer2 = customerRepository.insert(customer);
        log.info("Added customer {} successfully", customer2.getName());
        return customer2;
    }
}
