package com.my.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.aopdemo.dao.AccountDAO;
import com.my.aopdemo.dao.MembershipDAO;

public class AfterReturningDemoApp {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean form spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = theAccountDAO.findAccounts(false);
		
		System.out.println("\n\nMain Program : AfterReturningDemoApp");
		System.out.println("-------");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		//close the context
		context.close();
		
	}
	
}
