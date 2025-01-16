package com.bank.demo.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class CustomerResponse {

    private String customerId;
    //TODO need to add additional fields based on requirement like acccount and types of accounts etc if any or a flag to indicate if customer has account or not.
}
