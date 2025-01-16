package com.bank.demo.repo;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.demo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByDateOfBirthAndSsn(LocalDate dateOfBirth, String ssn);

    Optional<Customer> findByCustomerId(String customerId);

}

