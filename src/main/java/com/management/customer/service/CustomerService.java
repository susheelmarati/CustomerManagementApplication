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
        return customerRepository.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        customerRepository.saveAndFlush(customer);
        return customer;
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    public Customer findByEmail(String emailId) {
        return customerRepository.findByEmailId(emailId);
    }
}