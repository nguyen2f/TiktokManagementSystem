package com.ndc.tiktokmanagement.service;

import com.ndc.tiktokmanagement.model.Customer;
import com.ndc.tiktokmanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        customer.setFullName(customerDetails.getFullName());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        customerRepository.delete(customer);
    }
}