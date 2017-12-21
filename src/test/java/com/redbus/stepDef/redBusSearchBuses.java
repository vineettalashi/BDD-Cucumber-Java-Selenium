package com.redbus.stepDef;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.redbus.helper.base.BaseTest;
import com.redbus.module.searchBuses.SearchBusesModule;
import com.redbus.po.HomePage;
import com.redbus.utils.ConfigProvider;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class redBusSearchBuses extends BaseTest

{
	HomePage homePage;
	SearchBusesModule searchBusesHomePage;
	
	@Before
	public void initializeDriver()
	{
		super.initialize();
		homePage =  PageFactory.initElements(driver, HomePage.class);
		searchBusesHomePage = PageFactory.initElements(driver, SearchBusesModule.class);
	}
	
	@Given("^User is on RedBus homePage$")
	public void loginToRedBus()
	{
		
		launchApp(ConfigProvider.get("url"));
	}
	
	@When("^I enter source city and destination city$")
	public void enterCitiesAndDOJ(DataTable table)
	{	
		
		List<Map<String, String>> journies = new ArrayList<Map<String, String>>();
		journies = table.asMaps(String.class, String.class);
		
		for (Map J : journies) {
			searchBusesHomePage.enterAndSelectSourceCity(J.get(new ArrayList<String>(J.keySet()).get(0)).toString())
				.enterAndSelectDestinationCity(J.get(new ArrayList<String>(J.keySet()).get(1)).toString())
				.openCalenderAndsetDoJAsCurrentDay();

			homePage.searchBuses();
			homePage.verifySourceCityTextOnResultsPage(J.get(new ArrayList<String>(J.keySet()).get(0)).toString());
			homePage.verifyDestinationCityTextOnResultsPage(J.get(new ArrayList<String>(J.keySet()).get(1)).toString());
			driver.navigate().back();
		}
		
		/*for(List<String> l : data){
						
				searchBusesHomePage.enterAndSelectSourceCity(l.get(0))
				   					.enterAndSelectDestinationCity(l.get(1))
				   					.openCalenderAndsetDoJAsCurrentDay();

				homePage.searchBuses();
				homePage.verifySourceCityTextOnResultsPage(l.get(0));
				homePage.verifyDestinationCityTextOnResultsPage(l.get(1));
				driver.navigate().back();
			}*/
		}
	
	@Then("^Search Results are correct$")
	public void verifySearchResultsCities()
	{
		homePage.verifySourceCityTextOnResultsPage(ConfigProvider.get("src"));
		homePage.verifyDestinationCityTextOnResultsPage(ConfigProvider.get("dest"));
	}
	
	@After()
	public void closeBrowser()
	{
		//driver.quit();
	}
}
