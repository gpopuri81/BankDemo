package com.bank.demo.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class CreateAccountResponse {

    private String accountNumber;
    private String customerId;
    private String depositStatus;//TODO Define Enum for status

}
