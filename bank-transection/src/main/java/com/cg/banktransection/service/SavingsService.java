package com.cg.banktransection.service;

public interface SavingsService {


	Double deposit(double currentBalance, int accountNumber, double amount);

	Double withDraw(double currentBalance, Integer accountNumber, Double amount);

	
	
}
