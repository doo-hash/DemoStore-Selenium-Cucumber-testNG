package org.demoStore.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags="@all",
		features = "src/test/resources/features",
		plugin = {
				"pretty",
				"html:target/cucumberReports/htmlReport.html",
				"json:target/cucumberReports/jsonReport.json",
				"junit:target/cucumberReports/xmlReport.xml",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:test-output"				},
		glue = {
				"org/demoStore/stepDefinitions",
				"org/demoStore/hooks"
		}
	)

public class Runner extends AbstractTestNGCucumberTests {
	
	@DataProvider(parallel = true)
	public Object[][] scenario(){
		return super.scenarios();
	}
}
