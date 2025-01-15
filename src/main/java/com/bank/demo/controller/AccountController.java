package com.bank.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.model.Account;
import com.bank.demo.model.AccountTransfer;
import com.bank.demo.request.CreateBankAccountRequest;
import com.bank.demo.service.AccountService;

import jakarta.validation.Valid;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/createAccount")
	public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateBankAccountRequest request) {
		Account bankAccount = null;
		try {
		bankAccount = accountService.createBankAccount(request.getCustomerId(), request.getInitialDeposit());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
	}

	@PostMapping(value = "/transfer")
	public ResponseEntity<String> transferFunds(@RequestParam String fromAccountId, @RequestParam String toAccountId,
			@RequestParam double amount) {
		accountService.transferAmount(fromAccountId, toAccountId, amount);

		return new ResponseEntity<>(HttpStatus.OK);
		/*
		 * if (success) { return new ResponseEntity<>("Transfer successful",
		 * HttpStatus.OK); } else { return new ResponseEntity<>("Transfer failed",
		 * HttpStatus.BAD_REQUEST); }
		 */
	}

	@RequestMapping(value = "/accounts/{accountId}/transfers", method = RequestMethod.GET)
	public ResponseEntity<List<AccountTransfer>> getTransferHistory(@PathVariable String accountId) {
		List<AccountTransfer> tranferHistory = accountService.getTransferHistory(accountId);

		return new ResponseEntity<>(tranferHistory, HttpStatus.OK);

	}

}
