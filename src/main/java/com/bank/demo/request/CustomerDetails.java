package com.bank.demo.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CustomerDetails {
    
private String firstName;
private String lastName;
private String ssn;
private String dob;
}
