package com.redbus.helper.base;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.redbus.utils.ConfigProvider;

public class BasePageObject{
		
		WebDriver driver;
		public BasePageObject(WebDriver webdriver) {
			this.driver = webdriver;
		}
		
		void clickElement(By loc)
		{
			WebElement ele = getElement(loc);
			ele.click();
		}
		
		public WebElement getElement(By Locator)
		{	
			WebElement element = null;
			final By ElementLocator = Locator;
			
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
										.withTimeout(Integer.parseInt(ConfigProvider.get("FluentWait")), TimeUnit.SECONDS).pollingEvery(Integer.parseInt(ConfigProvider.get("PollingTime")), TimeUnit.MILLISECONDS)
										.ignoring(NoSuchElementException.class)
										.ignoring(StaleElementReferenceException.class);
			element = wait.until(new Function<WebDriver, WebElement>() 
			{
				public WebElement apply(WebDriver seldriver) { 
					WebElement ret = null;
					try
					{
						ret = PageObjectUtils.getDesiredElement(seldriver,ElementLocator);
						
					}
					catch(StaleElementReferenceException e)
					{
						e.printStackTrace();
					}
					catch(NoSuchElementException e)
					{
						e.printStackTrace();
					}
			         
					return ret;
					}
			 
			});
			
			if(element.isDisplayed() && element.isEnabled())		
				return element;
			else
				return null;
			
			
		}
		
		public List<WebElement> getListOfElement(By Locator)
		{	
			List<WebElement> elements = new ArrayList<WebElement>();
			final By ElementLocator = Locator;
			
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
										.withTimeout(Integer.parseInt(ConfigProvider.get("FluentWait")), TimeUnit.SECONDS).pollingEvery(Integer.parseInt(ConfigProvider.get("PollingTime")), TimeUnit.MILLISECONDS)
										.ignoring(NoSuchElementException.class)
										.ignoring(StaleElementReferenceException.class);
			elements = wait.until(new Function<WebDriver, List<WebElement>>() 
			{
				public  List<WebElement> apply(WebDriver seldriver) { 
					List<WebElement> listOfElements = new ArrayList<WebElement>();
					try
					{
						listOfElements = PageObjectUtils.getDesiredListOfElements(seldriver,ElementLocator);
						
					}
					catch(StaleElementReferenceException e)
					{
						e.printStackTrace();
					}
					catch(NoSuchElementException e)
					{
						e.printStackTrace();
					}
			         
					return listOfElements;
					}
			 
			});
			
			return elements;
			
			
		}

	}

