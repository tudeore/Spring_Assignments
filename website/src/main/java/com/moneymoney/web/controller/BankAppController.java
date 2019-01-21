package com.moneymoney.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.moneymoney.web.entity.Transaction;

@Controller
public class BankAppController {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public String depositForm() {
		return "DepositForm";
	}

	@RequestMapping("/deposit")
	public String deposit(@ModelAttribute Transaction transaction, Model model) {

		restTemplate.postForEntity("http://localhost:8989/transactions/deposit", transaction, null);
		model.addAttribute("message", "Success!");
		return "DepositForm";
	}

	/*
	 * @RequestMapping("/") public String withDraw() { return "withDrawForm"; }
	 */
	@RequestMapping("/WITHDRAW")
	public String withdrawForm() {
		return "withdrawForm";
	}

	@RequestMapping("/withdraw")
	public String withDraw(@ModelAttribute Transaction transaction, Model model) {

		restTemplate.postForEntity("http://localhost:8989/transactions/withdraw", transaction, null);
		model.addAttribute("message", "Success!");
		return "withdrawForm";
	}

	@RequestMapping("/FUNDTRANSFER")
	public String fundTransferForm() {

		return "fundTransferForm";
	}

	/*
	 * @RequestMapping("/fundTransfer") public String
	 * fundTransfer(@RequestParam("senderAccountNumber") int senderAccountNumber,
	 * 
	 * @RequestParam("receiverAccountNumber") int
	 * receiverAccountNumber, @RequestParam("amount") double amount, Model model) {
	 * 
	 * restTemplate .postForEntity(
	 * "http://localhost:8989/transactions/fundtransfer?senderAccountNumber=" +
	 * senderAccountNumber + "&&receiverAccountNumber=" + receiverAccountNumber +
	 * "&&amount=" + amount, null, null);
	 * 
	 * model.addAttribute("message", "Success!"); return "fundTransferForm"; }
	 */
	
	@RequestMapping("/fundTransfer")
	public String fundTransfer(@RequestParam("senderAccountNo") int senderAccountNo, @RequestParam("receiverAccountNo") int receiverAccountNo,
			@RequestParam("amount") double amount, Model model) {
		Transaction senderTransaction = new Transaction(senderAccountNo, amount);
		restTemplate.postForEntity("http://localhost:8989/transactions/withdraw", senderTransaction, null);
		Transaction receiverTransaction = new Transaction(receiverAccountNo, amount);
		restTemplate.postForEntity("http://localhost:8989/transactions", receiverTransaction, null);
		model.addAttribute("message","Success!");
		return "fundTransfer";
	}
		

	}


