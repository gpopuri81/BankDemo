package com.bank.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="bankAccounts")
public class Account {
	
	

	    @Id
	    private String id;
	    
	    private String customerId;
	    private double balance;
	    private List<AccountTransfer> tranferHistory;

	    public List<AccountTransfer> getTranferHistory() {
			return tranferHistory;
		}

		public void setTranferHistory(List<AccountTransfer> tranferHistory) {
			this.tranferHistory = tranferHistory;
		}

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
