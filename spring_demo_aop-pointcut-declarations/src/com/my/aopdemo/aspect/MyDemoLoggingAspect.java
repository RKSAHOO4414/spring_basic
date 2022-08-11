package com.my.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	//@Before("execution(public void updateAccount())")
	//@Before("execution(public void add*())")
	
	@Pointcut("execution(* com.my.aopdemo.dao.*.*(..)))")
	private void forDaoPackage() {}
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n=====>>> Executing @Before advice on method");
	}
	
	@Before("forDaoPackage()")
	public void performAPiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");
	}
	
}
