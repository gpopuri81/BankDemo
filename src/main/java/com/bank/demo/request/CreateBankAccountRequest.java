package com.bank.demo.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class CreateBankAccountRequest {
	@NotEmpty(message ="Customer ID is required and cannot be empty")
	private String customerId;
	@NotNull(message ="Initial deposit cannot be null")
	@Min(value=0,message ="Initial deposit must be greater than or equal to 0")
	private double initialDeposit;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(double initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	

}
