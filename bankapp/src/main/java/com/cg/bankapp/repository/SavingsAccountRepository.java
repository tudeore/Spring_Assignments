package com.cg.bankapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.bankapp.entity.Account;

public interface SavingsAccountRepository extends MongoRepository<Account, Integer> {

	
	
}
