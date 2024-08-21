package com.mini.banking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.banking.Exception.ExceptionHandling;
import com.mini.banking.Service.CustomerService;
import com.mini.banking.models.Account;
import com.mini.banking.models.AccountType;
import com.mini.banking.models.Customer;



@RestController("/")
public class WelcomeController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public String welcome() {
        boolean isBeanPresent = applicationContext.containsBean("customerService");
        System.out.println("Is CustomerService bean present: " + isBeanPresent);
        
        Account account = new Account(32000, AccountType.Savings);
        Customer c = customerService.createCustomer("shivu", "shivu", 20000,account.getAccountType());
        System.out.println(c.toString());
        return "welcome";
    }

    @PostMapping("/login")
    public Customer login(String username , String password) throws Exception{
        
        Customer  customer =customerService.findByName(username); 
        if(customer.getPassword().equals(password)==false)
            throw new ExceptionHandling("user not found");
        return customer ;
    
    }
}
