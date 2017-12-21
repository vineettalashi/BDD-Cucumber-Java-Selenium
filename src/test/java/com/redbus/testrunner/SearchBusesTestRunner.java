package com.redbus.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features/"},
				 glue={"com.redbus.stepDef"},
				 monochrome=true
				 
				 )
				
public class SearchBusesTestRunner {

}
