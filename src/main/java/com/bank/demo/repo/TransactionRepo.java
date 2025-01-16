package com.bank.demo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bank.demo.model.Transaction;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
    List<Transaction> findByAccountNumber(String accountNumber);
}
