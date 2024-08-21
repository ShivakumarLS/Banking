package com.mini.banking.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.banking.Exception.ExceptionHandling;
import com.mini.banking.Service.CustomerService;
import com.mini.banking.models.AccountType;
import com.mini.banking.models.CreateDTO;
import com.mini.banking.models.Customer;

@RestController
@RequestMapping("/")
public class CreateController {

    @Autowired
    CustomerService customerService;
    @PostMapping("/createcustomer")
    public Customer createCustomer(@RequestBody CreateDTO createDTO) throws Exception{
        String name = createDTO.getName(),
        password = createDTO.getPassword(),
        accountType = createDTO.getAccountType();
        AccountType acType=accountType.equalsIgnoreCase("savings")?AccountType.Savings:
                            accountType.equalsIgnoreCase("current")?AccountType.Current:null;
        if(acType == null){
            throw new Exception("Invalid Account type");
        }
        
        return customerService.createCustomer(name, password,0, acType);
    }
    
    @GetMapping("/getCustomers")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

}
