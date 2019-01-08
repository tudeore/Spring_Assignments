package com.moneymoney.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

@Repository
@Primary
public class SavingsAccountDAOJSImpl implements SavingsAccountDAO {
	
	@Autowired
	private JdbcTemplate template;
	@Override	
	public SavingsAccount createNewAccount(SavingsAccount account){
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",new Object[] {account.getBankAccount().getAccountNumber(),
							account.getBankAccount().getAccountHolderName(),
							account.getBankAccount().getAccountBalance(),account.isSalary(),
							null,"SA"});
		return account;
}
	@Override
	public SavingsAccount getAccountById(int accountNumber){
		return template.queryForObject("SELECT * FROM account where accountId=?",new Object[] {accountNumber},new SavingsAccountRowMappper());
		
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber){
		 template.update("DELETE FROM account WHERE accountId = ?", new Object[] {accountNumber});
		return null; 
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() {
		return 	template.query("SELECT * FROM ACCOUNT",new SavingsAccountRowMappper());
		
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance)  {
	template.update("UPDATE ACCOUNT SET account_balance=? where accountId=?",new Object[] {currentBalance,accountNumber});
	}

	@Override
	public void commit()  {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SavingsAccount updateAccount(int accountNumber, int choice, String name){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount serachAccount(String name, int choice) {
		return template.queryForObject("SELECT * FROM account WHERE account_hn =?", new Object[] {name},new SavingsAccountRowMappper() {
		});
	}

	@Override
	public List<SavingsAccount> searchAccountBySalary(int minSalary, int maxSalary){
		template.queryForObject("SELECT * FROM account WHERE account_balance BETWEEN ? AND ?", new Object[] {minSalary,maxSalary},new SavingsAccountRowMappper());//homeWork
		return null;
	}

	@Override
	public List<SavingsAccount> sortAccount(int choice, int choice2) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
