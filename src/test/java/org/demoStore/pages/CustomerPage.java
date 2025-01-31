package org.demoStore.pages;

import java.util.List;

import org.demoStore.utilities.HighlightElementClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CustomerPage extends BasePage {

	HighlightElementClass highlightElementClass = new HighlightElementClass();

	@FindBy(xpath = "//div[contains(@role,'alert')]")
	WebElement accountSuccesMessage;

	@FindBy(xpath = "//span[contains(text(),'Welcome')]")
	WebElement welcomeMessage;
	
	@FindBy(xpath = "//div[contains(@class,'box-content')]/p")
	WebElement contactInformation;
	
	@FindBy(xpath = "//span/button")
	WebElement accountDropdownButton;
	
	@FindBy(xpath = "//div[contains(@class,'customer-menu')]/ul[contains(@class,'header')]")
	WebElement accountDropdown;
	
	@FindBy(linkText = "Sign Out")
	WebElement logout;
	
	@FindBy(xpath = "//li/a[normalize-space()='My Orders']")
	WebElement myOrdersLink;
	
	@FindBy(xpath = "//table[@id='my-orders-table']")
	WebElement ordersTable;
	
	@FindBy(xpath = "//table[@id='my-orders-table']/thead/tr")
	List<WebElement> ordersTableRowHead;
	
	@FindBy(xpath = "//table[@id='my-orders-table']/thead/tr/th")
	List<WebElement> ordersTableColHead;
	
	@FindBy(xpath = "//table[@id='my-orders-table']/tbody/tr")
	List<WebElement> ordersTableRow;
	
	@FindBy(xpath = "//table[@id='my-orders-table']/tbody/tr/td")
	List<WebElement> ordersTableCol;
	
	@FindBy(xpath = "//a[@class='action  next' and @title='Next']")
	WebElement nextButton;

	@FindBy(xpath = "//a[@class='action  previous' and @title='Previous']")
	WebElement prevButton;

	@FindBy(xpath = "//ul[@class='items pages-items']/li[contains(@class,'item')]//span[2]")
	List<WebElement> orderPages;
	
	//constructor
	public CustomerPage(WebDriver driver) {
		super(driver);
	}
	
	//check whether element is displayed
	public boolean isMessageElementPresent() {
		return accountSuccesMessage.isDisplayed();
	}
	
	//return successful account creation message
	public String getCreateAccountSuccessMessage() {
		try {
			highlightElementClass.highlightElement(driver, accountSuccesMessage);
			String message = accountSuccesMessage.getText();
			return message;			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//check whether welcome element is displayed
	public boolean isWelcomeElementPresent() {
		return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();	
	}
	
	//get welcome message from page
	public String getWelcomeMessage() {
		try {
			highlightElementClass.highlightElement(driver, welcomeMessage);
			return welcomeMessage.getText();			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//check whether contact element is displayed
	public boolean iscontactElementPresent() {
		return contactInformation.isDisplayed();
	}
	
	//get contact info from page
	public String getContactInfo() {
		highlightElementClass.highlightElement(driver, contactInformation);
		return contactInformation.getText();
	}
	
	//click account dropdown button
	public void clickAccountDropdown() {
		accountDropdownButton.click();
	}
	
	//check whether account dropdown is displayed
	public boolean isDropdownVisible() {
		return accountDropdown.isDisplayed();
	}
	
	//click my orders link
	public void clickMyOrders() {
		myOrdersLink.click();
	}
	
	
	//logout
	public void clickLogoutLink() {
		highlightElementClass.highlightElement(driver, logout);
		logout.click();
	}
	
	//get orders
	public void getOrders() {
		executor.executeScript("arguments[0].scrollIntoView();", ordersTable);
		wait.until(ExpectedConditions.visibilityOf(ordersTable));
		int rowSize = ordersTableRow.size();
		int colSize = ordersTableColHead.size();
		
		String lastpage = orderPages.get(2).getText();
		highlightElementClass.highlightElement(driver, orderPages.get(2));
		System.out.println("last page : " + lastpage);

		
		while(nextButton != null) {
			for (int i = 0; i < ordersTableRowHead.size(); i++) {
				highlightElementClass.highlightElement(driver, ordersTableRowHead.get(i));
				for (int j = 0; j < colSize; j++) {
					highlightElementClass.highlightElement(driver, ordersTableColHead.get(j));
				}
			}
			
			
			for (int i = 0; i < rowSize; i++) {
				highlightElementClass.highlightElement(driver, ordersTableRow.get(i));
				for (int j = 0; j < colSize; j++) {
					highlightElementClass.highlightElement(driver, ordersTableCol.get(j));				
				}
			}
			
			if(!orderPages.get(3).getText().equals("3")) {
				highlightElementClass.highlightElement(driver, nextButton);
				nextButton.click();													
			}else if(orderPages.size() == 5 && orderPages.get(3).getText().equals("3")) {
				highlightElementClass.highlightElement(driver, nextButton);
				nextButton.click();																	
			}			
			else {
				break;
			}
		}
		
	}
}
