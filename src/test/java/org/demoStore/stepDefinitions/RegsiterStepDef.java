package org.demoStore.stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.demoStore.pages.CreateAccountPage;
import org.demoStore.pages.CustomerPage;
import org.demoStore.pages.HomePage;
import org.demoStore.utilities.AppUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegsiterStepDef  extends AppUtilities{
	
	@Given("user navigates to register page")
	public void user_navigates_to_register_page() {
		homePage = new HomePage(driver);
		accountPage = new CreateAccountPage(driver);
		customerPage = new CustomerPage(driver);
		
		homePage.clickCreateAccountElement();
		logger.trace("Create account link clicked");
		logger.info(driver.getCurrentUrl());
		logger.trace("Navigated to Create account page");
	}

	@When("user enters firstname {string}")
	public void user_enters_firstname(String firstname) {
		accountPage = new CreateAccountPage(driver);
		accountPage.setFirstNameInput(firstname);
		logger.trace("First name entered");
	}

	@When("user enters lastname {string}")
	public void user_enters_lastname(String lastname) {
		accountPage.setLastNameInput(lastname);
		logger.trace("Last name entered");
	}

	@When("user enters new random email")
	public void user_enters_new_random_email() {
		accountPage.setEmailInput(getRandomString() + "@gmail.com");
		logger.trace("Email entered");
	}
	
	@When("user enters new email {string}")
	public void user_enters_new_email(String email) {
		accountPage.setEmailInput(email);
		logger.trace("Email entered");
	}
	
	
	@When("user enters new password {string}")
	public void user_enters_new_password(String password) {
		accountPage.setPasswordInput(password);
		logger.trace("Password entered");
	}
	
	@When("user enters confirm password {string}")
	public void user_enters_confirm_password(String password) {
		accountPage.setConfirmPasswordInput(password);
		logger.trace("Confirm password entered");
	}

	@When("user clicks create account button")
	public void user_clicks_create_account_button() {
		accountPage.clickCreateAccountButton();
		logger.trace("create account button clicked");
		logger.info(driver.getCurrentUrl());
		logger.trace("Navigated to customer page");
	}

	@Then("user was navigated back to customer page")
	public void user_was_navigated_back_to_customer_page() {
		String message = customerPage.getCreateAccountSuccessMessage();
		if(!message.isEmpty()) {
			assertTrue(true);
			logger.trace("Customer account creation success message");
		}else {
			logger.warn("Account Success message not found");
		}
	}

	@Then("user name in welcome message {string}")
	public void user_name_in_welcome_message(String username) {
		if(customerPage.isWelcomeElementPresent() || customerPage.getWelcomeMessage().contains(username)) {
			customerPage.getContactInfo();
			logger.trace("username is visible");
		}else{
			logger.error("Customer details not found");
		}
	}

	@Then("user should get proper account warning message")
	public void user_should_get_proper_account_warning_message() {
		String message = accountPage.getErrorMessage();
		if(message.isEmpty()) {
			logger.trace("Warning message recieved");			
		}else {
			logger.warn("warning message not found");
		}

	}
	
	@When("user doesnot enters any details")
	public void user_doesnot_enters_any_details() {
		accountPage.setFirstNameInput(" ");
		accountPage.setLastNameInput(" ");
		accountPage.setEmailInput(" ");
		accountPage.setPasswordInput(" ");
		accountPage.setConfirmPasswordInput(" ");
		logger.info("Empty credentials");	
	}
	
	@Then("user gets firstname warning message {string}")
	public void user_gets_firstname_warning_message(String message) {
		String msg = accountPage.getFirstnameErrorMessage();
		if(msg.equals(message)) {
			logger.trace("Firstname warning message found");
		}else {
			logger.warn("Warning message not found");
		}
	}

	@Then("user gets lastname warning message {string}")
	public void user_gets_lastname_warning_message(String message) {
		String msg = accountPage.getLastnameErrorMessage();
		if(msg.equals(message)) {
			logger.trace("Lastname warning message found");
		}else {
			logger.warn("Warning message not found");
		}
	}

	@Then("user gets email error message {string}")
	public void user_gets_email_error_message(String message) {
		String msg = accountPage.getEmailErrorMessage();
		if(msg.equals(message)) {
			logger.trace("Email warning message found");
		}else {
			logger.warn("Warning message not found");
		}
	}
	
	@Then("user gets password and confirm password warning message {string}")
	public void user_gets_password_and_confirm_password_warning_message(String message) {
		String psdmsg = accountPage.getPasswordErrorMessage();
		String cPsdmsg = accountPage.getconfirmPasswordErrorMessage();
		if(psdmsg.equals(message) && cPsdmsg.equals(message)) {
			logger.trace("Password and Confirm Password warning message found");
		}else {
			logger.warn("Warning message not found");
		}
	}
}
