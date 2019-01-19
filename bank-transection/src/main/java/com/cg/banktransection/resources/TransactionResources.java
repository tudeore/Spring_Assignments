package com.cg.banktransection.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.banktransection.service.SavingsService;
import com.cg.banktransection.transaction.Transaction;

@RestController
@RequestMapping("/transaction")
public class TransactionResources {

	@Autowired
	SavingsService service;

	@Autowired
	private RestTemplate template;

	@PostMapping
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {

		ResponseEntity<Double> balance = template
				.getForEntity("http://localhost:8080/accounts/" + transaction.getAccountNumber()+ "/balance", Double.class);
		double currentBalance = balance.getBody();
		
		Double newBalance = service.deposit(currentBalance, transaction.getAccountNumber(), transaction.getAmount());
		
		template.put("http://localhost:8080/accounts/" + transaction.getAccountNumber()+"?currentBalance="+newBalance,HttpStatus.OK);
		return null;
	}


}
