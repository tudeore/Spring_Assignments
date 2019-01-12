package com.moneymoney.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.moneymoney.exception.InsufficientFundsException;
import com.moneymoney.exception.InvalidInputException;

@Aspect
@Component
public class ValidationSavingsAccountService {

	Logger logger = Logger.getLogger(ValidationSavingsAccountService.class.getName());

	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void deposit(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("In Arrond");
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
		if ((Double) parameter[1] > 0) {
			pjp.proceed();
		} else {
			logger.info("invalid input");
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}
	}

	@Around("execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.fundTransfer (..))")
	public void fundTransfer(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameter = pjp.getArgs();
		if ((Double) parameter[2] > 0) {
			pjp.proceed();
		} else {
			logger.info("In Exception");
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		}

	}

	@AfterThrowing(pointcut = "execution(* com.moneymoney.account.service.SavingsAccountServiceImpl.withdraw (..))", throwing = "ex")
	public void exceptions(JoinPoint jp, Throwable ex) {
		logger.info("This is exp" + ex);
	}

}
