package com.cg.banktransection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.banktransection.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

	
	
}
