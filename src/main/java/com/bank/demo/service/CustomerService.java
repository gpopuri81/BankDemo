package com.bank.demo.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.exception.CustomerNotFoundException;
import com.bank.demo.model.Customer;
import com.bank.demo.repo.CustomerRepository;
import com.bank.demo.response.CustomerResponse;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse findCustomerByDobAndSsn(String dateOfBirth, String ssn) {
        Optional<Customer> customer = customerRepository.findByDateOfBirthAndSsn(LocalDate.parse(dateOfBirth), ssn);
        if (customer.isPresent()) {
            return CustomerResponse.builder().customerId(customer.get().getCustomerId()).build(); //Returning basic information 
        } else {
            throw new CustomerNotFoundException("Customer not found with DOB and SSN ");
        }
    }
}

