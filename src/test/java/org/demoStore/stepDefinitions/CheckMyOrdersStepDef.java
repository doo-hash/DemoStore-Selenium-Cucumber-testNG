package org.demoStore.stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.demoStore.pages.CustomerPage;
import org.demoStore.pages.HomePage;
import org.demoStore.pages.LogoutSuccessPage;
import org.demoStore.utilities.AppUtilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckMyOrdersStepDef extends AppUtilities {

	@When("user clicks on my account")
	public void user_clicks_on_my_account() {
		homePage = new HomePage(driver);
		homePage.clickAccountDropdown();
		homePage.clickMyAccountLink();
		logger.trace("Clicked my account link");
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/customer/account/"));
		assertTrue(driver.getTitle().equals("My Account"));
		logger.info(driver.getCurrentUrl());
		logger.trace("Navigated to customer account page");
	}

	@When("user clicks on my orders")
	public void user_clicks_on_my_orders() {
		customerPage = new CustomerPage(driver);
		customerPage.clickMyOrders();
		logger.trace("Clicked my orders link");
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/sales/order/history/"));
		assertTrue(driver.getTitle().equals("My Orders"));
		logger.info(driver.getCurrentUrl());
		logger.trace("Navigated to orders page");
	}

	@Then("user checks his orders")
	public void user_checks_his_orders() throws InterruptedException {
		customerPage.getOrders();
		logger.trace("Checked my orders");
	}

	@Then("user logs out")
	public void user_logs_out() {
		logoutSuccessPage = new LogoutSuccessPage(driver);
		customerPage.clickAccountDropdown();
		wait.until(d -> customerPage.isDropdownVisible());

		customerPage.clickLogoutLink();
		logger.trace("Clicked logout button");
		
		assertTrue(logoutSuccessPage.isSignoutMessagePresent());			
		logger.info("logout successful");
	}

	
}
