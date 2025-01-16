package com.bank.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Document(collection ="bankAccounts")
@Data
@SuperBuilder
@NoArgsConstructor
public class Account {
	
	    @Id
	    private String id;
	    private String customerId;
	    private double balance;
	    private List<AccountTransfer> tranferHistory;
		private String accountNumber;

}
