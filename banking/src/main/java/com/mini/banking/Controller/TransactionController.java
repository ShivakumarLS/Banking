package com.mini.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.banking.Service.CustomerService;
import com.mini.banking.Service.TransactionService;
import com.mini.banking.models.Customer;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    CustomerService customerService;

    @PostMapping("sendmoney")
    public String sendMoney(long from, long to, long ammount) {
        return transactionService.sendMoney(from, to, ammount);
    }

    public String deposit(String username, String password, long accountNumber, long ammount) throws Exception {
        Customer customer = customerService.findByName(username);
        if (customer.getPassword().equals(password) == false) {
            throw new Exception("transaction failed , invalid credentials");
        }
        return transactionService.deposit(username, password, ammount);
    }

    public long withdraw(String username, String password, long ammount) throws Exception {
        Customer customer = customerService.findByName(username);
        if (customer.getPassword().equals(password) == false) {
            throw new Exception("transaction failed .., invalid credentials");
        }
        long accountNumber = customer.getAccount().getAccountNumber();

        return transactionService.withdraw(accountNumber, ammount);
    }
        
}
