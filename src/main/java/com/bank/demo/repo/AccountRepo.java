package com.bank.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bank.demo.model.Account;

public interface AccountRepo extends MongoRepository<Account, String> {

	    List<Account> findByCustomerId(String customerId);
		Optional<Account> findByAccountNumber(String accountNumber);
	}