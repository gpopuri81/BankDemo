package com.bank.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.demo.exception.CustomerNotFoundException;
import com.bank.demo.model.Account;
import com.bank.demo.model.AccountTransfer;
import com.bank.demo.model.Customer;
import com.bank.demo.repo.AccountRepo;
import com.bank.demo.repo.CustomerRepository;
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

	
	public CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest) {
		 if (createAccountRequest instanceof CreateAccountExistingCustomerRequest){
			 CreateAccountExistingCustomerRequest createAccountExistingCustomerRequest = (CreateAccountExistingCustomerRequest) createAccountRequest;
			 //Check if customer exists
			 customerRepository.findByCustomerId(createAccountExistingCustomerRequest.getCustomerId())
					 .orElseThrow(() -> new CustomerNotFoundException("Customer not found with given id"));
			 Account bankAccount = Account.builder().customerId(createAccountExistingCustomerRequest.getCustomerId()).accountNumber(UUID.randomUUID().toString())//Need to bank specific account number generator
					 .balance(createAccountExistingCustomerRequest.getBalance()).build();
					 accountRepository.save(bankAccount);

			 if (StringUtil.isNullOrEmpty(createAccountExistingCustomerRequest.getExistingAccountNumber())){
				//TODO Save transaction with Pending Deporsit status
				//Create a transaction with status PENDING DEPOSIT
			 } else {
				//TODO check balance on existing account and transfer amount to new account
				// if Amount is available then transaction status is COMPLETED else PENDING DEPOSIT
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

}
