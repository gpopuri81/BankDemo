package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.model.Account;
import com.bank.repo.AccountRepo;

@Service
public class AccountService {

	
	
	@Autowired
    private AccountRepo accountRepository;

    public Account createBankAccount(String customerId, double initialDeposit) {
    	Account bankAccount = new Account(customerId, initialDeposit);
        return accountRepository.save(bankAccount);
    }

    public boolean transferFunds(String fromAccountId, String toAccountId, double amount) {
        Optional<Account> fromAccountOpt = accountRepository.findById(fromAccountId);
        Optional<Account> toAccountOpt = accountRepository.findById(toAccountId);

        if (!fromAccountOpt.isPresent() || !toAccountOpt.isPresent()) {
            return false;
        }

        Account fromAccount = fromAccountOpt.get();
        Account toAccount = toAccountOpt.get();

        if (fromAccount.getBalance() < amount) {
            return false; 
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return true;
    }

    public List<Account> getAccountsByCustomer(String customerId) {
        return accountRepository.findByCustomerId(customerId);
    }
}

