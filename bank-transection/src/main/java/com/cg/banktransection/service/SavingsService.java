package com.cg.banktransection.service;

import com.cg.banktransection.transaction.Transaction;

public interface SavingsService {


	Double deposit(double currentBalance, int accountNumber, double amount);

	
	
}
