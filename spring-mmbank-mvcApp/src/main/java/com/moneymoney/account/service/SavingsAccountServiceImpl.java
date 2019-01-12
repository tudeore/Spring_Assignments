package com.moneymoney.account.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.factory.AccountFactory;
import com.moneymoney.account.util.DBUtil;
import com.moneymoney.dao.SavingsAccountDAO;
import com.moneymoney.exception.AccountNotFoundException;
import com.moneymoney.exception.InsufficientFundsException;
import com.moneymoney.exception.InvalidInputException;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {
	Logger logger = Logger.getLogger(SavingsAccountServiceImpl.class.getName());
	
	private AccountFactory factory;

	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();

	}

	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	@Transactional
	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(),
				account.getBankAccount().getAccountBalance() + amount);
	}

	@Transactional
	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException ,InvalidInputException{
		double CurrentBalance = account.getBankAccount().getAccountBalance();
		if(CurrentBalance>=amount)
		{
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(),
				(account.getBankAccount().getAccountBalance() - amount));
		} else 
			throw new InvalidInputException("Insufficient Balance");
	}

	@Transactional
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {
		try {
			withdraw(sender, amount);
			deposit(receiver, amount);
			DBUtil.commit();
		} catch (InvalidInputException | InsufficientFundsException e) {
			e.printStackTrace();
			DBUtil.rollback();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.rollback();
		}
	}

	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	public SavingsAccount updateAccount(int accountNumber, int choice, String name)
			throws SQLException, ClassNotFoundException {
		return savingsAccountDAO.updateAccount(accountNumber, choice, name);
	}

	public SavingsAccount searchAccount(String name, int choice) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.serachAccount(name, choice);
	}

	public List<SavingsAccount> searchAccountBySalary(int minSalary, int maxSalary)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.searchAccountBySalary(minSalary, maxSalary);
	}

	public List<SavingsAccount> sortAccount(int choice, int choice2) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortAccount(choice, choice2);
	}

}
