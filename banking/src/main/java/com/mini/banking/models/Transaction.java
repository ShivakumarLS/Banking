package com.mini.banking.models;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
    

    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long transactionId;

    private long fromAccount;
    
    private String from;
    
    private long toAccount;
    
    private long amount;
    
    private Instant timestamp;
    
    public long getFromAccount() {
        return fromAccount;
    }
    public void setFromAccount(long fromAccount) {
        this.fromAccount = fromAccount;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public long getToAccount() {
        return toAccount;
    }
    public void setToAccount(long toAccount) {
        this.toAccount = toAccount;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public Transaction(long fromAccount, String from, long toAccount, long amount,Instant Timestamp) {
        this.fromAccount = fromAccount;
        this.from = from;
        this.toAccount = toAccount;
        this.amount = amount;
        this.timestamp=timestamp;
    }

}
