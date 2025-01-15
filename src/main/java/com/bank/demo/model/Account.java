package com.bank.demo.model;

import org.springframework.data.annotation.Id;

public class Account {
	
	

	    @Id
	    private String id;
	    private String customerId;
	    private double balance;

	    public Account(String customerId, double deposit) {
	        this.customerId = customerId;
	        this.balance = deposit;
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

	
	

}
