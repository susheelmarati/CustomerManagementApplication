package com.management.customer.service;

import com.management.customer.entity.Customer;
import com.management.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id));
    }

    public Customer addCustomer(Customer customer) {
        customer.setId(UUID.randomUUID());
        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(UUID.fromString(id));
    }

    public Customer updateCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public Customer findByEmail(String emailId) {
        return customerRepository.findByEmailId(emailId);
    }
}