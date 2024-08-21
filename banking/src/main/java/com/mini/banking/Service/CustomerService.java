package com.mini.banking.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mini.banking.Exception.ExceptionHandling;
import com.mini.banking.Repository.CustomerRepository;
import com.mini.banking.models.Account;
import com.mini.banking.models.AccountType;
import com.mini.banking.models.Customer;
import com.mini.banking.models.Transaction;
import com.mini.banking.utility.EncryptionUtility;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionService transactionService;

    public Customer createCustomer(String name, String password, long initialDeposit, AccountType accountType) {
        Account account = new Account(initialDeposit, accountType);

        Customer customer = customerRepository.save(new Customer(name, EncryptionUtility.encode(password), account));
        System.out.println(EncryptionUtility.matches(password, customer.getPassword()));
        return customer;
    }

    public long checkBalance(String username, String password) {
        return customerRepository.findByName(username).getAccount().getBalance();
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public String changePassword(String name, String oldPassword, String password) {
        Customer customer = customerRepository.findByName(name);
        if (!(oldPassword.equals(customer.getPassword()))) {
            return "Incorrect password";
        }
        customer.setPassword(password);
        customerRepository.save(customer);
        return "Changed..!!!";
    }

    public String sendMoney(String username, String password, long toAccountNumber, long ammount) {

        if (customerRepository.findByName(username).getAccount().getBalance() < ammount)
            return "Insufficient Balance";

        if (!(customerRepository.findByName(username).equals(customerRepository.findByPassword(password))))
            return "Insufficient Balance";

        Customer customer = customerRepository.findByName(username);
        transactionService.sendMoney(customer.getAccount().getAccountNumber(), toAccountNumber, ammount);
        return "Money Sent";

    }

    public String deposit(String username, String password, long money) {
        Customer customer = customerRepository.findByName(username);
        if (!(customer.getPassword().equals(password))) {
            throw new ExceptionHandling("invalid credentials");
        }
        Account account = customer.getAccount();
        this.sendMoney(username, password, customer.getAccount().getAccountNumber(), money);
        return "deposited";
    }

    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer  customer = customerRepository.findByName(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(customer.getName(), customer.getPassword(),
                new ArrayList<>());
    }
}
