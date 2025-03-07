package org.demoStore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutSuccessPage extends BasePage {
	
	private static String url = "https://magento.softwaretestingboard.com/customer/account/logoutSuccess/";
	
	@FindBy(xpath = "//span[contains(@class,'base')]")
	WebElement signOutMessage;
	
	@FindBy(xpath = "//p[2]")
	WebElement redirectMessage;
	
	//Constructor
	public LogoutSuccessPage(WebDriver driver) {
		super(driver);
	}
	
	//Check URL
	public boolean isUrl() {
		return driver.getCurrentUrl().equals(url);
	}
	
	//Check successful Signout message is present
	public boolean isSignoutMessagePresent() {
		return signOutMessage.isDisplayed();
	}
	
	//get signout message
	public String getSignOutMessage() {
		return signOutMessage.getText();
	}
	
	//Check redirect message is present
	public boolean isRedirectMessagePresent() {
		return redirectMessage.isDisplayed();
	}
	
	//get redirect message
	public String getRedirectMessage() {
		return redirectMessage.getText();
	}
}
