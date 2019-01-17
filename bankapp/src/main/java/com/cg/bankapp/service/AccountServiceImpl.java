package com.cg.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.repository.SavingsAccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private SavingsAccountRepository accountRepository;
	
	
	@Override
	public void addSavingsAccount(Account account) {
		
		  accountRepository.save(account);
	}


	@Override
	public List<Account> getAllSavingsAccount() {
		
		return accountRepository.findAll();
	}

}
