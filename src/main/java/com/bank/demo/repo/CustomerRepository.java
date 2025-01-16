package com.bank.demo.repo;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.demo.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByDobAndSsn(String dob, String ssn);

}

