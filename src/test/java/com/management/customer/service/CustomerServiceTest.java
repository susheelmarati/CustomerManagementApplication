package com.management.customer.service;

import com.management.customer.entity.Customer;
import com.management.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testAddCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setEmailId("stevesmith@gmail.com");
        customer.setFirstName("steve");
        customer.setLastName("smith");
        customer.setMobile("+120 23343533");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer result = customerService.addCustomer(customer);
        assertEquals("steve", result.getFirstName());
        assertEquals("stevesmith@gmail.com", result.getEmailId());
        assertEquals("+120 23343533", result.getMobile());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testGetCustomerById() {
        Customer customer = new Customer();
        UUID uuid = UUID.randomUUID();
        customer.setId(uuid);
        customer.setEmailId("stevesmith@gmail.com");
        customer.setFirstName("steve");
        customer.setLastName("smith");
        customer.setMobile("+120 23343533");

        when(customerRepository.findById(uuid)).thenReturn(Optional.of(customer));

        Optional<Customer> result = customerService.getCustomerById(String.valueOf(uuid));

        assertTrue(result.isPresent());
        assertEquals("steve", result.get().getFirstName());
        assertEquals("smith", result.get().getLastName());
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        UUID uuid = UUID.randomUUID();
        when(customerRepository.findById(uuid)).thenReturn(Optional.empty());

        Optional<Customer> result = customerService.getCustomerById(String.valueOf(uuid));
        assertFalse(result.isPresent());
    }

    @Test
    void getAllCustomers() {
        Customer customer1 = new Customer();
        UUID uuid = UUID.randomUUID();
        customer1.setId(uuid);
        customer1.setEmailId("stevesmith@gmail.com");
        customer1.setFirstName("steve");
        customer1.setLastName("smith");
        customer1.setMobile("+120 23343533");

        Customer customer2 = new Customer();
        uuid = UUID.randomUUID();
        customer2.setId(uuid);
        customer2.setEmailId("johnmarkAnthony@gmail.com");
        customer2.setFirstName("john");
        customer2.setMiddleName("anthony");
        customer2.setLastName("mark");
        customer2.setMobile("+120 23343533");

        List<Customer> customerList = List.of(customer1, customer2);
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.getAllCustomers();
        assertEquals(result, customerList);
    }

    @Test
    void deleteCustomer() {

        UUID uuid = UUID.randomUUID();
        customerService.deleteCustomer(String.valueOf(uuid));
        verify(customerRepository, times(1)).deleteById(uuid);
    }

    @Test
    void updateCustomer() {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setEmailId("stevesmith@gmail.com");
        customer.setFirstName("steve");
        customer.setLastName("smith");
        customer.setMobile("+120 23343533");
        when(customerRepository.save(customer)).thenReturn(customer);


        Customer result = customerService.updateCustomer(customer);

        assertEquals("steve", result.getFirstName());
        assertEquals("stevesmith@gmail.com", result.getEmailId());
        assertEquals("+120 23343533", result.getMobile());
        verify(customerRepository, times(1)).save(customer);
    }
}