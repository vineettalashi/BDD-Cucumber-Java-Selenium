package com.redbus.po;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.redbus.helper.base.BasePageObject;



public class HomePage extends BasePageObject{
	
	
	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	enum ELEMENT 
	{
		source_field(By.xpath("//input[@id='src']")),
		dest_field(By.xpath("//input[@id='dest']")),
		DoJ_field(By.xpath("//span[@class='fl icon-calendar_icon-new icon-onward-calendar icon']")),
		search_button(By.xpath("//button[@id='search_btn']")),
		doj_current_day(By.xpath("//div[@id='rb-calendar_onward_cal']//td[@class='current day']")),
		Dynamic_source_city_list(By.xpath("//div[input[@id='src']]//li")),
		Dynamic_destination_city_list(By.xpath("//div[input[@id='dest']]//li")),
		Source_city_text(By.xpath("//div[@class='fl meta-det' and i[@class='icon icon-right-arrow']]/span[1]")),
		Destination_city_text(By.xpath("//div[@class='fl meta-det' and i[@class='icon icon-right-arrow']]/span[2]")),
		;
		private By findBy;
		
		public By getLocator()
		{
			return findBy;
			
		}
		private ELEMENT(By locator) {
			this.findBy=locator;
		}
	}
	
	public boolean verifyDestinationCityTextOnResultsPage(String dest)
	{
		WebElement element = getElement(ELEMENT.Destination_city_text.getLocator());
		String DestCity= null;
		boolean flag=false;
		if(null!=element && element.isDisplayed())
		{
			DestCity = element.getText().trim();
			if(DestCity.equals(dest))
			{
				flag=true;
			}
		}
		
		return flag;
	}
	
	
	public boolean verifySourceCityTextOnResultsPage(String src)
	{
		WebElement element = getElement(ELEMENT.Source_city_text.findBy);
		String SourceCity= null;
		boolean flag=false;
		if(null!=element && element.isDisplayed())
		{
			SourceCity = element.getText().trim();
			if(SourceCity.equals(src))
			{
				flag=true;
			}
		}
		
		return flag;
	}
	
	public void selectDesiredSourceCityFromAutoFillSugesstions(String city) {
		List<WebElement> elements = getListOfElement(ELEMENT.Dynamic_source_city_list.findBy);
		for(int i=1;i<=elements.size();i++)
		{
			WebElement element = getElement(By.xpath("//div[input[@id='src']]//li["+i+"]"));
			if(element.getText().equals(city)){
				element.click();
				break;
			}
		}
	
	}
	
	public void selectDesiredDestinationCityFromAutoFillSugesstions(String city) {
		List<WebElement> elements = getListOfElement(ELEMENT.Dynamic_destination_city_list.findBy);
		for(int i=1;i<=elements.size();i++)
		{
			WebElement element = getElement(By.xpath("//div[input[@id='dest']]//li["+i+"]"));
			if(element.getText().equals(city)){
				element.click();
				break;
			}
		}
	
	}
	
	
	public void setSource(String src) {
		WebElement element = getElement(ELEMENT.source_field.findBy);
		element.sendKeys(src);
	}

	public void setDestination(String dest) {
		WebElement dest_field = getElement(ELEMENT.dest_field.findBy);
		dest_field.sendKeys(dest);
	}
	
	public void setDoJAsCurrentDay(){
			
		WebElement dojCurrentDay = getElement(ELEMENT.doj_current_day.findBy);
		dojCurrentDay.click();
		
	}
	
	public void openCalender(){
		WebElement dojourney = getElement(ELEMENT.DoJ_field.findBy);
		dojourney.click();
	}
	public void searchBuses() {
		WebElement dojourney = getElement(ELEMENT.search_button.findBy);
		dojourney.click();
	}
	
	

}
