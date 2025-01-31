package org.demoStore.pages;

import org.demoStore.utilities.HighlightElementClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductPage extends BasePage {

	HighlightElementClass highlightElement = new HighlightElementClass();
	
	@FindBy(xpath = "//span[contains(@class,'base')]")
	WebElement productName;
		
	@FindBy(xpath = "(//li[contains(@class,'product-item')]//button)[1]")
	WebElement addToCartButton;
	
	
	//Constructor
	public ProductPage(WebDriver driver) {
		super(driver);
	}

	//get Product name from product page
	public String getProductName() {
		highlightElement.highlightElement(driver, productName);
		return productName.getText();
	}
	
	
	public void clickAddToCartButton() {
		highlightElement.highlightElement(driver, addToCartButton);
		addToCartButton.click();
	}
}
