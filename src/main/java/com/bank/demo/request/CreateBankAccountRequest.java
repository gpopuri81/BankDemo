package com.bank.demo.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CreateBankAccountRequest {
	
	private String customerId;
	private String existingAccountNumber;
	private CustomerDetails customerDetails;

	@NotNull(message ="Initial deposit cannot be null")
	@Min(value=0,message ="Initial deposit must be greater than or equal to 0")
	private double initialDeposit;
	
	

}
