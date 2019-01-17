package com.cg.bankapp.service;

import java.util.List;

import com.cg.bankapp.entity.Account;

public interface AccountService {

	List<Account> getAllSavingsAccount();

	void addSavingsAccount(Account account);

	
}
