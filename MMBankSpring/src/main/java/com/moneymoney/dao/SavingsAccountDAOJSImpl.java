package com.moneymoney.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.AccountNotFoundException;

public class SavingsAccountDAOJSImpl implements SavingsAccountDAO {
	
	@Autowired
	private JdbcTemplate template;
	@Override	
	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		template.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)",new Object[] {account.getBankAccount().getAccountNumber(),
							account.getBankAccount().getAccountHolderName(),
							account.getBankAccount().getAccountBalance(),account.isSalary(),
							null,"SA"});
		return account;
}
	@Override
	public SavingsAccount getAccountById(int accountNumber)throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return template.queryForObject("SELECT * FROM account where accountId=?",new Object[] {accountNumber},new SavingsAccountRowMappper());
		
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		 template.update("DELETE FROM account WHERE accountId = ?", new Object[] {accountNumber});
		return null; 
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return 	template.query("SELECT * FROM ACCOUNT",new SavingsAccountRowMappper());
		
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
	
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SavingsAccount updateAccount(int accountNumber, int choice, String name)
			throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SavingsAccount serachAccount(String name, int choice) throws ClassNotFoundException, SQLException {
		return template.queryForObject("SELECT * FROM account WHERE account_hn =?", new Object[] {name},new SavingsAccountRowMappper() {
		});
	}

	@Override
	public List<SavingsAccount> searchAccountBySalary(int minSalary, int maxSalary)
			throws SQLException, ClassNotFoundException {
		//template.queryForObject("SELECT * FROM account WHERE account_balance BETWEEN ? AND ?", requiredType)//homeWork
		return null;
	}

	@Override
	public List<SavingsAccount> sortAccount(int choice, int choice2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
