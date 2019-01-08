package com.moneymoney.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.moneymoney.account.SavingsAccount;
import com.moneymoney.exception.InsufficientFundsException;
import com.moneymoney.exception.InvalidInputException;

@Aspect
@Component
public class ValidationSavingsAccountService {

	Logger logger = Logger.getLogger(ValidationSavingsAccountService.class.getName());

	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.deposit (..))")
	public void deposit(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameter = pjp.getArgs();
		if ((Double) parameter[1] > 0) {
			pjp.proceed();
		} else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}

	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.withdraw (..))")
	public void withdraw(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameter = pjp.getArgs();
		SavingsAccount savingsAccount =  (SavingsAccount) parameter[0];
		double currentBalance = savingsAccount.getBankAccount().getAccountBalance();
		if ((Double) parameter[1] > 0 && currentBalance >= (double)parameter[1]) {
			pjp.proceed();
		} else {
//			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
			logger.info("invalid input");
		}
	}
}
