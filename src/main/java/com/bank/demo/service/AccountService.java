package com.bank.demo.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.exception.CustomerNotFoundException;
import com.bank.demo.model.Account;
import com.bank.demo.model.AccountTransfer;
import com.bank.demo.model.Customer;
import com.bank.demo.model.Transaction;
import com.bank.demo.model.TransactionStatus;
import com.bank.demo.model.TransactionType;
import com.bank.demo.repo.AccountRepo;
import com.bank.demo.repo.CustomerRepository;
import com.bank.demo.repo.TransactionRepo;
import com.bank.demo.request.CreateAccountExistingCustomerRequest;
import com.bank.demo.request.CreateAccountNewCustomerRequest;
import com.bank.demo.request.CreateAccountRequest;
import com.bank.demo.response.CreateAccountResponse;

import ch.qos.logback.core.util.StringUtil;

@Service
public class AccountService {

	@Autowired
	private AccountRepo accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private TransactionRepo transactionRepository;

	
	public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
		 if (createAccountRequest instanceof CreateAccountExistingCustomerRequest){
			 CreateAccountExistingCustomerRequest createAccountExistingCustomerRequest = (CreateAccountExistingCustomerRequest) createAccountRequest;
			 //Check if customer exists
			 customerRepository.findByCustomerId(createAccountExistingCustomerRequest.getCustomerId())
					 .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given id"));
			 Account bankAccount = Account.builder().customerId(createAccountExistingCustomerRequest.getCustomerId()).accountNumber(UUID.randomUUID().toString())//Need to bank specific account number generator
					 .balance(createAccountExistingCustomerRequest.getBalance()).build();
					 accountRepository.save(bankAccount);

			//Ideally these needs to move to backend process like events using camel or some MQ systems

			 if (StringUtil.isNullOrEmpty(createAccountExistingCustomerRequest.getExistingAccountNumber())){
				Transaction transaction = Transaction.builder().accountNumber(bankAccount.getAccountNumber())
						.amount(Double.valueOf(createAccountExistingCustomerRequest.getBalance().doubleValue()))
						.transactionDate(OffsetDateTime.now()).transactionStatus(TransactionStatus.PENDING).description("initial deposit")
						.transactionType(TransactionType.CREDIT).build();
						transactionRepository.save(transaction); //Handle tranasction creation failure vai Camel or some MQ system
			 } else {
				Optional<Account> optionalAccount = accountRepository.findByAccountNumber(createAccountExistingCustomerRequest.getExistingAccountNumber());
				if (optionalAccount.isPresent() && checkBalanceExists(optionalAccount.get(), createAccountExistingCustomerRequest.getBalance())){
				
						Transaction creditTransaction = Transaction.builder().accountNumber(createAccountExistingCustomerRequest.getExistingAccountNumber())
						.amount(Double.valueOf(createAccountExistingCustomerRequest.getBalance().doubleValue()))
						.transactionDate(OffsetDateTime.now()).transactionStatus(TransactionStatus.COMPLETED).description("With Drawal for new account setup") 
						.transactionType(TransactionType.DEBIT).build();
						Transaction debitTransaction = Transaction.builder().accountNumber(bankAccount.getAccountNumber())
						.amount(Double.valueOf(createAccountExistingCustomerRequest.getBalance().doubleValue()))
						.transactionDate(OffsetDateTime.now()).transactionStatus(TransactionStatus.COMPLETED).description("initial deposit")
						.transactionType(TransactionType.CREDIT).build();
						transactionRepository.saveAll(List.of(creditTransaction, debitTransaction)); //Handle tranasction creation failure vai Camel or some MQ system
				} else {
					Transaction transaction = Transaction.builder().accountNumber(bankAccount.getAccountNumber())
					.amount(Double.valueOf(createAccountExistingCustomerRequest.getBalance().doubleValue()))
					.transactionDate(OffsetDateTime.now()).transactionStatus(TransactionStatus.PENDING).description("initial deposit")
					.transactionType(TransactionType.CREDIT).build();
					transactionRepository.save(transaction); //Handle tranasction creation failure vai Camel or some MQ system
				}
				
			 }
			return CreateAccountResponse.builder().accountNumber(bankAccount.getAccountNumber()).customerId(createAccountExistingCustomerRequest.getCustomerId()).build();
		 }
		 else {
			 CreateAccountNewCustomerRequest createAccountNewCustomerRequest = (CreateAccountNewCustomerRequest) createAccountRequest;
			 Customer customer = Customer.builder().dateOfBirth(createAccountNewCustomerRequest.getDateOfBirth()).
			 firstName(createAccountNewCustomerRequest.getFirstName())
			 .lastName(createAccountNewCustomerRequest.getLastName())
			 .customerId(UUID.randomUUID().toString())//Need to generate unique customer id as bank specifications
			 .ssn(createAccountNewCustomerRequest.getSsn()).build();
			 customer = customerRepository.save(customer);
			 Account bankAccount = Account.builder().customerId(customer.getCustomerId()).accountNumber(UUID.randomUUID().toString())//Need to bank specific account number generator
			 .balance(createAccountNewCustomerRequest.getBalance()).build();
			 accountRepository.save(bankAccount);
			 return CreateAccountResponse.builder().accountNumber(bankAccount.getAccountNumber()).customerId(customer.getCustomerId()).build();

		 }
	}

	private boolean checkBalanceExists(Account account, float balance) {
		return account.getBalance() > balance;
	}

}
