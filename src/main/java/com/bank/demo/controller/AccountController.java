package com.bank.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.demo.model.Account;
import com.bank.demo.model.AccountTransfer;
import com.bank.demo.request.CreateAccountRequest;
import com.bank.demo.request.CreateBankAccountRequest;
import com.bank.demo.response.CreateAccountResponse;
import com.bank.demo.service.AccountService;

import jakarta.validation.Valid;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping(value = "/createAccount")
	public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
		CreateAccountResponse bankAccount = accountService.createAccount(request);
		return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
	}


}
