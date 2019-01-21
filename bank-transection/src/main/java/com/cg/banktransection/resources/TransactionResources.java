package com.cg.banktransection.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.banktransection.service.SavingsService;
import com.cg.banktransection.transaction.Transaction;

@RestController
@RequestMapping("/transactions")
public class TransactionResources {

	@Autowired
	SavingsService service;

	@Autowired
	private RestTemplate template;

	@PostMapping("/deposit")
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction) {

		ResponseEntity<Double> balance = template.getForEntity(
				"http://localhost:8080/accounts/" + transaction.getAccountNumber() + "/balance", Double.class);
		double currentBalance = balance.getBody();

		Double newBalance = service.deposit(currentBalance, transaction.getAccountNumber(), transaction.getAmount());

		template.put(
				"http://localhost:8080/accounts/" + transaction.getAccountNumber() + "?currentBalance=" + newBalance,
				null);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withDraw(@RequestBody Transaction transaction) {

		ResponseEntity<Double> balance = template.getForEntity(
				"http://localhost:8080/accounts/" + transaction.getAccountNumber() + "/balance", Double.class);
		double currentBalance = balance.getBody();

		Double newBalance = service.withDraw(currentBalance, transaction.getAccountNumber(), transaction.getAmount());

		template.put(
				"http://localhost:8080/accounts/" + transaction.getAccountNumber() + "?currentBalance=" + newBalance,
				null);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	
	@PostMapping("/fundtransfer")
	public ResponseEntity<Transaction> fundTransfer(@RequestParam int senderAccountNumber,
			@RequestParam int receiverAccountNumber, @RequestParam double amount) {

		ResponseEntity<Double> balance = template
				.getForEntity("http://localhost:8080/accounts/" + senderAccountNumber + "/balance", Double.class);
		double currentBalance = balance.getBody();

		Double newBalance = service.withDraw(currentBalance, senderAccountNumber, amount);

		template.put("http://localhost:8080/accounts/" + senderAccountNumber + "?currentBalance=" + newBalance, null);

		ResponseEntity<Double> balance2 = template
				.getForEntity("http://localhost:8080/accounts/" + receiverAccountNumber + "/balance", Double.class);
		double currentBalance2 = balance2.getBody();

		Double newBalance2 = service.deposit(currentBalance2, receiverAccountNumber, amount);

		template.put("http://localhost:8080/accounts/" + receiverAccountNumber + "?currentBalance=" + newBalance2,
				null);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
