package com.moneymoney.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.account.service.SavingsAccountService;
import com.moneymoney.exception.AccountNotFoundException;

@Controller
public class SavingsAccountController {

	@Autowired
	SavingsAccountService savingsAccountService;

	@RequestMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/addNewSA")
	public String createAccount() {
		return "createNewAccount";
	}

	@RequestMapping("/createAccountForm")
	public String addNewacc(@RequestParam("txtAccHN") String accountHolderName,
			@RequestParam("txtBalance") double accountBalance, @RequestParam("rdSalary") boolean salary)
			throws ClassNotFoundException, SQLException {
		savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		return "redirect:getAll";
	}

	@RequestMapping("/getAll")
	public String createNewAccount(Model model) throws ClassNotFoundException, SQLException

	{
		List<SavingsAccount> account = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts", account);
		return "accountDetails";
	}
	
	@RequestMapping("/CloseAccount")
	public String closeAccount() {
		
		return "closeAccount";
	}
		
	@RequestMapping("/deleteForm")
	public String deleteAccount(@RequestParam("accountNumber")int accountNumber) throws ClassNotFoundException, SQLException {
	savingsAccountService.deleteAccount(accountNumber);	
	System.out.println("****Account closed******");
	return "deleteMessage";
	}
	
	@RequestMapping("/deposit")
	public String depositForm() {
	
		return"depositForm";
	}
	
	@RequestMapping("/depositAmount")
	public String depositAmount(@RequestParam("accountNumber") int accountNumber,@RequestParam("amount") double amount) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		savingsAccountService.deposit(savingsAccountService.getAccountById(accountNumber), amount);
		System.out.println("deposit Succefully");
	
		return "depositAmount";
	}
	
	@RequestMapping("/withdraw")
	public String withdrawForm() {
	
		return"withdrawForm";
	}
	
	@RequestMapping("/withdrawAmount")
	public String withdraw(@RequestParam("accountNumber") int accountNumber,@RequestParam("amount") double amount) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		
		savingsAccountService.withdraw(savingsAccountService.getAccountById(accountNumber), amount);
		System.out.println("Withdraw Amount Succesfully");
		return "withdrawSuccesful";
		
	}
	
	@RequestMapping("/GetCurrentBalance")
	public String getCurrentBalanceForm() {
		
		return "getCurrentBalanceForm";
	}
	
	@RequestMapping("/getAccountBalance")
	public String getAccountBalance(@RequestParam("accountNumber") int accountNumber, Model model) throws ClassNotFoundException, AccountNotFoundException, SQLException {
		
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "displayCurrentBalance";
		
	}
	
	@RequestMapping("/getAccountById")
	public String serachAccount() {
	
		return "getAccountByIdForm";
		
	}
	
	@RequestMapping("/getAccount")
	public String getaccountById(@RequestParam("accountNumber") int accountNumber,Model model) throws ClassNotFoundException, AccountNotFoundException, SQLException {
		
	SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
	model.addAttribute("account",account);
		return "accountDetails";
		
	}
	
	@RequestMapping("/searchByName")
	public String searchAccountByName() {
		
		return "searchByName";
	}
	
	@RequestMapping("/getAccountByName")
	public String getAccountByName(@RequestParam("accountHolderName") String accountHolderName,Model model) throws ClassNotFoundException, SQLException {
		
		SavingsAccount account = savingsAccountService.searchAccount(accountHolderName, 0);
		model.addAttribute("account",account);
		return "accountDetails";
	}
	
	@RequestMapping("/updateSA")
	public String updateAccount() {
		
		return "updateSAForm";
	}
	
	@RequestMapping("/updateSAAccountForm")
	public String updateAccountForm(@RequestParam("accountNumber") int accountNumber,Model model) throws ClassNotFoundException, AccountNotFoundException, SQLException {
		
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account",account);
		return"accountDetails";
		
	}
	
	
	@RequestMapping("/updateAccountOne")
	public String updateAccountOne(@RequestParam("accountNumber") int accountNumber,@RequestParam("newName") String accountHolderName,
					@RequestParam("accountBalance") int accountBalance,@RequestParam("newSalaried") String salaried,
					@RequestParam("accountType") String accountType) throws ClassNotFoundException, SQLException{
		
		boolean salariedOrNot = salaried.equalsIgnoreCase("yes")?true:false;
		SavingsAccount accountToUpdate = new SavingsAccount(accountNumber, accountHolderName, accountBalance, salariedOrNot);
		accountToUpdate = savingsAccountService.updateAccount(accountNumber, accountBalance, accountType);
		return "accountDetails";
		
	}
	
	@RequestMapping("/fundTransfer")
	public String fundTransferForm() {
		
		return"fundTransferForm";
		
	}
	
	@RequestMapping("/fundTransferDone")
	public String fundTransfer(@RequestParam("accountNumberOne") int senderAccountNumber,@RequestParam("accountNumberTwo") int receiverAccountNumber,
			@RequestParam("amount") double amount) throws ClassNotFoundException, AccountNotFoundException, SQLException {
				
		SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
		SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
		savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
		return "fundTransferSuccesful";
		
		
		
	}
	
	
	
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


