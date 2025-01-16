package com.bank.demo.model;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@Document(collection = "transactionInfo")
public class Transaction {
    @Id
    private String transactionId;
    private String accountId;
    private OffsetDateTime transactionDate;
    private Double amount;
    private String merchant;
    private TransactionType transactionType; // e.g., "purchase", "refund"
}
