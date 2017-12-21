package com.redbus.module.searchBuses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.redbus.po.HomePage;

public class SearchBusesModule {
	
	private HomePage homePage;
	private WebDriver driver;
	
	public SearchBusesModule(WebDriver SeleniumDriver) {
		
		this.driver=SeleniumDriver;
		homePage= PageFactory.initElements(this.driver, HomePage.class);
	}
	
	public SearchBusesModule enterAndSelectSourceCity(String SrcCity){
			
		homePage.setSource(SrcCity);
		homePage.selectDesiredSourceCityFromAutoFillSugesstions(SrcCity);
		
		return this;
	}
	
	public SearchBusesModule enterAndSelectDestinationCity(String DestCity){
		
		homePage.setDestination(DestCity);
		homePage.selectDesiredDestinationCityFromAutoFillSugesstions(DestCity);
		
		return this;
	}
	
	
	
	public SearchBusesModule openCalenderAndsetDoJAsCurrentDay(){
		
		homePage.openCalender();
		homePage.setDoJAsCurrentDay();
		
		return this;
	}
}
