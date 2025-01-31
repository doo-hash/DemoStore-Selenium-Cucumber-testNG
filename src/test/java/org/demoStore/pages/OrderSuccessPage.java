package org.demoStore.pages;

import org.demoStore.utilities.HighlightElementClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OrderSuccessPage extends BasePage {

	HighlightElementClass highlightElement = new HighlightElementClass();
	
		
	@FindBy(xpath = "//a[contains(@class,'order-number')]")
	WebElement orderNumberLink;
	
	public OrderSuccessPage(WebDriver driver) {
		super(driver);
	}

	// get order number
	public String getOrderNumber() {
		highlightElement.highlightElement(driver, orderNumberLink);
		return orderNumberLink.getText();
	}
	
	//click ordernumber Link
	public void clickOrderNumber() {
		orderNumberLink.click();
	}
}
