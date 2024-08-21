package com.mini.banking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.banking.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
