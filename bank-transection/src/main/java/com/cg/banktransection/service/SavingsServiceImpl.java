package com.cg.banktransection.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.banktransection.repository.TransactionRepository;
import com.cg.banktransection.transaction.Transaction;
import com.cg.banktransection.transaction.TransactionType;

public class SavingsServiceImpl implements SavingsService {

	@Autowired
	private TransactionRepository repository;

	@Override
	public Double deposit(double currentBalance, int accountNumber, double amount) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance += amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setTransactionDetails("deposited successfully");
		repository.save(transaction);
		
		return currentBalance;
	}

}
