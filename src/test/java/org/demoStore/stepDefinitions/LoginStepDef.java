package org.demoStore.stepDefinitions;


import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.demoStore.pages.HomePage;
import org.demoStore.pages.LoginPage;
import org.demoStore.utilities.AppUtilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDef  extends AppUtilities{
	
	@Given("user navigates to login page")
	public void user_navigates_to_login_page() {
		wait = new WebDriverWait(driver, Duration.ofMillis(3000));
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);

		homePage.clickLoginElement();
		logger.trace("Clicked login link");
		logger.info(driver.getCurrentUrl());
	}

	@When("user enters email {string}")
	public void user_enters_email(String email) {
		loginPage.setEmailInput(email);
		logger.trace("Email entered");
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		loginPage.setPasswordInput(password);
		logger.trace("Password entered");
	}

	@When("user clicks login button")
	public void user_clicks_login_button() {
		loginPage.clickLoginButton();
		logger.trace("Clicked login button");
	}

	@Then("user was navigated back to home page")
	public void user_was_navigated_back_to_home_page() {
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/"));
		logger.trace("Navigated back to home page");
		logger.info(driver.getCurrentUrl());

	}
	
	@Then("user name is visible {string}")
	public void user_name_is_visible(String username) {
		String message = homePage.getWelcomeMessage();
		if(message.contains(username)) {
			assertTrue(true);
			logger.trace("Username found");
		}else {
			logger.fatal("Username - " + username + " not found");
		}
	}

	@Then("user should get proper warning message")
	public void user_should_get_proper_warning_message() {
		String errMessage = loginPage.getErrorMessage();
		if(errMessage.length() != 0) {
			logger.trace("Warnig message found: "+ errMessage);
		}else {
			logger.warn("Warning message not found");
		}
	}

	@When("user doesnot enters credentials")
	public void user_doesnot_enters_credentials() {
		loginPage.setEmailInput(" ");
		loginPage.setPasswordInput(" ");
		logger.info("Login with empty credentials");
	}

	@Then("user gets email warning message {string}")
	public void user_gets_email_warning_message(String errMessage) {
		String message = loginPage.getEmailErrorMessage();
		if(message.equals(errMessage)) {
			 logger.trace("Email warning message found"); 
		}else{
			 logger.warn("warning message not found"); 
		}
	}

	@Then("user gets password warning message {string}")
	public void user_gets_password_warning_message(String errMessage) {
		String message = loginPage.getPasswordErrorMessage();
		if(message.equals(errMessage)) {
			 logger.trace("Password warning message found"); 
		}else {
			logger.warn("Warning message not found");
		}
	}
}
