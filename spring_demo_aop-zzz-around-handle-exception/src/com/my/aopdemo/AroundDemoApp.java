package com.my.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.my.aopdemo.dao.AccountDAO;
import com.my.aopdemo.dao.MembershipDAO;
import com.my.aopdemo.service.TrafficFortuneService;

public class AroundDemoApp {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean form spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("\nMain Program: AroundDemoApp");
		
		System.out.println("Calling getFortune");
		
		String data = theFortuneService.getFortne();
		
		System.out.println("\n My fortune is : "+data);
		
		System.out.println("finished");
				
		//close the context
		context.close();
		
	}
	
}
