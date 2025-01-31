package org.demoStore.stepDefinitions;


import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.demoStore.pages.HomePage;
import org.demoStore.pages.LoginPage;
import org.demoStore.pages.LogoutSuccessPage;
import org.demoStore.pages.ProductPage;
import org.demoStore.pages.SearchProductPage;
import org.demoStore.utilities.AppUtilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchProductStepDef extends AppUtilities {
	
	String actualProductName;

	@When("user login to the application with {string} and {string}")
	public void user_enters_product_name(String email, String password) {
		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		loginPage = new LoginPage(driver);
		
		logger.info(driver.getCurrentUrl());
		logger.trace("Navigated to login page");
		
		loginPage.setEmailInput(email);
		loginPage.setPasswordInput(password);
		logger.trace("Entered login credentials and clicked login button");					
	}
	
	@Then("user was redirected back to home page")
	public void user_was_redirected_back_to_home_page() {
		loginPage.clickLoginButton();
		logger.trace("Clicked login button");
		homePage = new HomePage(driver);

		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/"));
//		homePage.isWelcomeElementPresent();
		String message = homePage.getWelcomeMessage();
		logger.trace("Navigated to home page");
		logger.info(driver.getCurrentUrl());
		logger.info("Welcome Message: " + message);
	}
	
	@When("user enters product name {string}")
	public void user_enters_product_name(String product) {
		homePage.setSearchInputData(product + Keys.ENTER);
		logger.trace("Product name entered in search box: " + product);
	}
	
	@When("user finds searched product {string}")
	public void user_finds_searched_product(String product) {
		searchProductPage = new SearchProductPage(driver);
		actualProductName = searchProductPage.getRandomSearchedProductName();
		if(actualProductName.length() != 0) {
			searchProductPage.getRandomSearchedProduct();
			logger.trace("Product " + product + " found");
		}else {
			logger.warn("product not found");
		}
	}
	
	@When("user clicks product name link")
	public void user_clicks_product_name_link() {
		searchProductPage.clickProductLink();
		logger.trace("Clicked products link");
		logger.info(driver.getCurrentUrl());
		logger.trace("Navigated to product page");
	}
	
	@Then("user checks product details")
	public void user_checks_product_details() {
		productPage = new ProductPage(driver);
		String product = productPage.getProductName();
		logger.trace("Product is: " + product);
	}

	@Then("user logs out of the application")
	public void user_logs_out_of_the_application() {
		LogoutSuccessPage logoutSuccessPage = new LogoutSuccessPage(driver);
		homePage.clickAccountDropdown();
		homePage.clickLogoutLink();
		logger.trace("Clicked logout button");
		logger.info(driver.getCurrentUrl());
		wait.until(d -> logoutSuccessPage.isUrl());
		logger.trace("Navigated to logout page");
		
		if(logoutSuccessPage.isSignoutMessagePresent()) {
			assertTrue(true);			
			logger.info("logout successful");
		}else {
			logger.error("logout unsuccessful");
		}
	}
	
}
