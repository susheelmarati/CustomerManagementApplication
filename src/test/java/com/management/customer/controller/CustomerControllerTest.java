package com.management.customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.customer.entity.Customer;
import com.management.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Test
    void getAllCustomers() throws Exception {

        }

    @Test
    void getCustomerById() {
    }

    @Test
    void createCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setEmailId("stevesmith@gmail.com");
        customer.setFirstName("steve");
        customer.setLastName("smith");
        customer.setMobile("+120 23343533");

        when(customerService.addCustomer(any())).thenReturn(customer);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        final String customerJson = new ObjectMapper().writeValueAsString(customer);

        mockMvc.perform(post("/v1/customer/add")
                        .contentType("application/json")
                        .content(customerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("steve"))
                .andExpect(jsonPath("$.emailId").value("stevesmith@gmail.com"));
    }

    @Test
    void updateCustomer() throws Exception {
        UUID uuid = UUID.randomUUID();
        Customer customer1 = new Customer();
        customer1.setId(uuid);
        customer1.setEmailId("stevesmith@gmail.com");
        customer1.setFirstName("steve");
        customer1.setLastName("smith");
        customer1.setMobile("+120 23343533");

        Customer customer2 = new Customer();
        customer2.setId(uuid);
        customer2.setEmailId("stevesmith1234@gmail.com");
        customer2.setFirstName("steve");
        customer2.setLastName("smith");
        customer2.setMobile("+120 23343533");

        when(customerService.getCustomerById(String.valueOf(uuid))).thenReturn(Optional.of(customer1));
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
        final String customerJson = new ObjectMapper().writeValueAsString(customer2);

        mockMvc.perform(put("/v1/customer/" + uuid)
                        .contentType("application/json")
                        .content(customerJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("steve"))
                .andExpect(jsonPath("$.emailId").value("stevesmith1234@gmail.com"));

        verify(customerService, times(1)).updateCustomer(customer1);
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void testDeleteCustomer() {
    }
}