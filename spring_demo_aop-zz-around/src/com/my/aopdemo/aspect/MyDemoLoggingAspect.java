package com.my.aopdemo.aspect;

import java.util.List;

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
	
	@Around("execution(* com.my.aopdemo.service.*.getFortune(..))")
	public Object arroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
		
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Excuting @Around on method : "+method);
		
		long begin = System.currentTimeMillis();
		
		Object result = theProceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		System.out.println("\n=====> Duration: " + duration/1000.0 + " seconds");
		
		return result;
	}
	
	
	
	
	@After("execution(* com.my.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Excuting @After (finally) on method : "+method);
		
	}
	
	@AfterThrowing(
			pointcut = "execution(* com.my.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Excuting @AfterThrowing on method : "+method);
		
		System.out.println("\n=====>>> The exception is : "+theExc);
	}
	
	@AfterReturning(
			pointcut = "execution(* com.my.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n=====>>> Excuting @AfterReturning on method : "+method);
		
		System.out.println("\n=====>>> result is : " + result);
		
		ConvertAccountNamesToUpperCase(result);
		
		System.out.println("\n=====>>> result is : " + result);
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
		System.out.println("\n=====>>> Executing @Before advice on method");
		
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method : "+methodSig);
		
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object tempArg : args) {
			System.out.println(tempArg);
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				
				System.out.println("account name : "+theAccount.getName());
				System.out.println("account level : "+theAccount.getLevel());
			}
		}
		
	}
	
}










