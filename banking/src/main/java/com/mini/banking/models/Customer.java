package com.mini.banking.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private int customerId;

    @Column(unique =true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "accountNumber")
    @JsonManagedReference // Allows serialization of account in Customer
    private Account account;

    private String password;

    public Customer() {}

    public Customer(String name, String password, Account account) {
        this.name = name;
        this.password = password;
        this.account = account;
    }

    // Getters and Setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // equals and hashCode methods

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return customerId == other.customerId &&
               Objects.equals(name, other.name) &&
               Objects.equals(password, other.password);
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + ", account=" + account.toString() ;
    }
}
