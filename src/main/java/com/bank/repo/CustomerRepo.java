package com.bank.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.demo.model.Customer;

public interface CustomerRepo extends MongoRepository<Customer, String> {
}

