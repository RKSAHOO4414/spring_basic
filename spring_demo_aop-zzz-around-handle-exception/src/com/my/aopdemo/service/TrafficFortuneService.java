package com.my.aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {
	
	public String getFortne() {
		
		try {
			
			TimeUnit.SECONDS.sleep(5);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Expect heavy traffic this morning";  
		
	}

	public String getFortne(boolean tripWire) {
		
		if(tripWire) {
			throw new RuntimeException("Major accident! Highway is closed!");
		}
		
		return getFortne();
	}
	
}
