package com.mini.banking.Service;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.banking.Exception.ExceptionHandling;
import com.mini.banking.Repository.AccountRepository;
import com.mini.banking.Repository.CustomerRepository;
import com.mini.banking.Repository.TransactionRepository;
import com.mini.banking.models.Account;
import com.mini.banking.models.Customer;
import com.mini.banking.models.Transaction;

@Service
public class TransactionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String sendMoney(long fromAccountNumber, long toAccountNumber, long ammount) {
        Account from = accountRepository.findByAccountNumber(fromAccountNumber);

        Account to = accountRepository.findByAccountNumber(fromAccountNumber);

        Customer customer = customerRepository.findByAccount(from);
        Instant timestamp = Instant.now();

        Transaction transaction = new Transaction(fromAccountNumber, customer.getName(), toAccountNumber, ammount,
                timestamp);

        if (from.getBalance() < ammount)
            new Exception("Insufficient Balance");

        from.setBalance(from.getBalance() - ammount);
        to.setBalance(to.getBalance() + ammount);
        transactionRepository.save(transaction);
        return "Sent";

    }

    public String deposit(String username, String password, long money) {
        Customer customer = customerRepository.findByName(username);
        if (!(customer.getPassword().equals(password))) {
            throw new ExceptionHandling("invalid credentials");
        }
        Account account = customer.getAccount();
        long accountNumber = customer.getAccount().getAccountNumber();
        this.sendMoney(accountNumber,accountNumber, money);
        return "deposited";
    }

    public long withdraw(long fromAccountNumber, long ammount) throws Exception {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Customer customer = customerRepository.findByAccount(fromAccount);

        fromAccount.setBalance(fromAccount.getBalance() - ammount);
        System.out.println("balance remaining : "+fromAccount.getBalance());
        return ammount;
    }
}
