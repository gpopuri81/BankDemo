package com.bank.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.model.Account;
import com.bank.demo.model.AccountTransfer;
import com.bank.demo.model.Customer;
import com.bank.demo.repo.AccountRepo;
import com.bank.demo.repo.CustomerRepo;

@Service
public class AccountService {

	@Autowired
	private AccountRepo accountRepository;

	@Autowired
	private CustomerRepo customerRepository;

	public Account createBankAccount(String customerId, double initialDeposit) {
		Account bankAccount = new Account(customerId, initialDeposit);
		bankAccount = accountRepository.save(bankAccount);
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));
		customer.getBankAccountIds().add(bankAccount.getId());
		customerRepository.save(customer);
		return bankAccount;

	}

	public void  transferAmount(String fromAccountId, String toAccountId, double amount) {
		Account fromAccountOpt = accountRepository.findById(fromAccountId)
				.orElseThrow(() -> new RuntimeException("From account not found"));
		
		Account toAccountOpt = accountRepository.findById(toAccountId)
				.orElseThrow(() -> new RuntimeException("To account not found"));
		

		if(fromAccountOpt.getBalance()<amount)
		{
			throw new RuntimeException("Insufficient Balance");
		}

		fromAccountOpt.setBalance(fromAccountOpt.getBalance() - amount);
		toAccountOpt.setBalance(toAccountOpt.getBalance() + amount);
        AccountTransfer accountTransfer= new AccountTransfer();
        accountTransfer.setAmount(amount);
        accountTransfer.setFromAccountId(fromAccountId);
        accountTransfer.setToAccountId(toAccountId);
        accountTransfer.setTimestamp(String.valueOf(System.currentTimeMillis()));
        
        fromAccountOpt.getTranferHistory().add(accountTransfer);
        toAccountOpt.getTranferHistory().add(accountTransfer);
		accountRepository.save(fromAccountOpt);
		accountRepository.save(toAccountOpt);

		
		
	}

	
	public List<AccountTransfer> getTransferHistory(String accountId) {
		Account bankAccount=accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
		return bankAccount.getTranferHistory();
		
	}
}
