package com.bank.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.model.Account;
import com.bank.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ResponseEntity<Account> createAccount(@RequestParam String customerId, @RequestParam double initialDeposit) {
		Account bankAccount = accountService.createBankAccount(customerId, initialDeposit);
		return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ResponseEntity<String> transferFunds(@RequestParam String fromAccountId, @RequestParam String toAccountId,
			@RequestParam double amount) {
		boolean success = accountService.transferFunds(fromAccountId, toAccountId, amount);

		if (success) {
			return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Transfer failed", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Account>> getAccounts(@PathVariable String customerId) {
		List<Account> accounts = accountService.getAccountsByCustomer(customerId);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
}
