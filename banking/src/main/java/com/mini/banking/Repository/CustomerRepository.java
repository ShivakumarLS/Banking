package com.mini.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.banking.models.Account;
import com.mini.banking.models.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    Customer findByAccount(Account account);

    Customer findByName(String name);

    Customer findByPassword(String pass);

    
}
