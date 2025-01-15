package com.bank.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection ="customers")
public class Customer {

    @Id
    private String id;
    private String name;
    private List<String> bankAccountIds;
	public List<String> getBankAccountIds() {
		return bankAccountIds;
	}
	public void setBankAccountIds(List<String> bankAccountIds) {
		this.bankAccountIds = bankAccountIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

 
}