package com.my.springDemo;

public class BaseballCoach implements Coach {
	
	private FortuneService fortuneService;
	
	public BaseballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkOut() {
		return "Spend 30 minute on batting practice";
	}

	@Override
	public String getDailyWorkFortune() {
		
		return fortuneService.getFortune();
	}
}
