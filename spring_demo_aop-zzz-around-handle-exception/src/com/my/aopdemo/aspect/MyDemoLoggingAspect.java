package com.my.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.my.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.my.aopdemo.service.*.getFortune(..))")
	public Object arroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Excuting @Around on method : "+method);
		
		long begin = System.currentTimeMillis();
		
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		}catch(Exception e) {
			
			myLogger.warning(e.getMessage());
			
			throw e;
			
		}
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		myLogger.info("\n=====> Duration: " + duration/1000.0 + " seconds");
		
		return result;
	}
	
	
	
	
	@After("execution(* com.my.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Excuting @After (finally) on method : "+method);
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.my.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Excuting @AfterThrowing on method : "+method);
		
		myLogger.info("\n=====>>> The exception is : "+theExc);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.my.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n=====>>> Excuting @AfterReturning on method : "+method);
		
		myLogger.info("\n=====>>> result is : " + result);
		
		ConvertAccountNamesToUpperCase(result);
		
		myLogger.info("\n=====>>> result is : " + result);
	}
	
	private void ConvertAccountNamesToUpperCase(List<Account> result) {
		
		//loop through accounts
		for(Account tempAccount : result) {
			
			//get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();
			
			//update the name on the account
			tempAccount.setName(theUpperName);
			
		}
		
	}

	@Before("com.my.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {		
		myLogger.info("\n=====>>> Executing @Before advice on method");
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Method : "+methodSig);
		
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object tempArg : args) {
			myLogger.info(tempArg.toString());
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				
				myLogger.info("account name : "+theAccount.getName());
				myLogger.info("account level : "+theAccount.getLevel());
			}
		}
		
	}
	
}










