package com.my.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.aopdemo.dao.AccountDAO;
import com.my.aopdemo.dao.MembershipDAO;

public class AfterThrowingDemoApp {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean form spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		List<Account> theAccounts = null;
		
		try {
			boolean tripWire = true;
			
			theAccounts = theAccountDAO.findAccounts(tripWire);
			
		}catch(Exception e) {
			System.out.println("\n\n Main Program ... caught exception : " + e);
		}
		
		System.out.println("\n\nMain Program : AfterThrowingDemoApp");
		System.out.println("-------");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		//close the context
		context.close();
		
	}
	
}
