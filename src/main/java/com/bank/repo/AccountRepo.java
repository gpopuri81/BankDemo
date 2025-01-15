package com.bank.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.demo.model.Account;

public interface AccountRepo extends MongoRepository<Account, String> {

	    List<Account> findByCustomerId(String customerId);
	}