package com.my.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoAPP {
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("tennisCoach", Coach.class);
		
		boolean result = (theCoach == alphaCoach);
		
		System.out.println("\nPointing to the same object : "+result);
		
		System.out.println("\nmemory location for the coach : "+theCoach);
		
		System.out.println("\nmemory location for alphaCoach" + alphaCoach + "\n");
		
		context.close();
		
	}
}
