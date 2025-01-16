package com.bank.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AccountTransfer {
	
	private String fromAccountId;
    private String toAccountId;
    private double amount;
    private String timestamp;

}
