package com.bank.demo.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document(collection ="customers")
@Data
@SuperBuilder
@NoArgsConstructor
public class Customer {

    @Id
    private String id;  //dont expose this to API Response.
    private String firstName;
	private String lastName;
	//Using them as unique identifiers to identify customer. Need to enhance more authentication data for customer uniqueness
	private LocalDate dateOfBirth;
	private String ssn;
    //have to add customer fields like address, phone number, email etc.
    private List<String> bankAccountIds;
	private String customerId;
 
}