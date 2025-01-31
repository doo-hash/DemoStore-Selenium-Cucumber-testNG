package org.demoStore.pages;

import org.demoStore.utilities.HighlightElementClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HomePage extends BasePage {

	HighlightElementClass highlightElementClass = new HighlightElementClass();
	
	@FindBy(linkText = "Create an Account")
	WebElement createAccount;
	
	@FindBy(linkText = "Sign In")
	WebElement signInLink;
	
	@FindBy(xpath = "//span[contains(text(),'Welcome')]")
	WebElement welcomeMessage;
		
	@FindBy(xpath = "//button[contains(@class,'switch')]")
	WebElement accountDropdownButton;

	@FindBy(xpath = "//div[contains(@class,'customer-menu')]/ul[contains(@class,'header')]")
	WebElement accountDropdown;
	
	@FindBy(linkText = "My Account")
	WebElement myAccount;
	
	@FindBy(linkText = "Sign Out")
	WebElement logout;

	@FindBy(id = "search")
	WebElement searchInput;
	
	@FindBy(xpath = "//a[contains(@class,'showcart')]")
	WebElement cartButton;
	
	@FindBy(xpath = "//span[contains(@class,'counter-number')]")
	WebElement cartCount;
	
	@FindBy(xpath = "//a[@title='Remove item']")
	WebElement removeItem;
	
	@FindBy(xpath = "//button[@class='action-primary action-accept']")
	WebElement modalOkButton;
	
	@FindBy(xpath = "//strong[@class='subtitle empty']")
	WebElement cartEmptyMessage;
	
	@FindBy(id = "btn-minicart-close")
	WebElement minicartClose;
	
	
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}

	//check url
	public boolean isUrl(String url) {
		return driver.getCurrentUrl().equals(url);
	}
	
	//click create an account link
	public void clickCreateAccountElement() {
		highlightElementClass.highlightElement(driver, createAccount);
		createAccount.click();;
	}
	
	//click login link
	public void clickLoginElement() {
		highlightElementClass.highlightElement(driver, signInLink);
		signInLink.click();;
	}
	
	//check whether welcome element is displayed
	public boolean isWelcomeElementPresent() {
		return(wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed());
	}
	
	//get welcome message from page
	public String getWelcomeMessage() {
		try {
			highlightElementClass.highlightElement(driver, welcomeMessage);
			return(welcomeMessage.getText());			
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	//click account dropdown button
	public void clickAccountDropdown() {
		accountDropdownButton.click();
	}
	
	//check whether account dropdown is displayed
	public boolean isDropdownVisible() {
		return accountDropdown.isDisplayed();
	}
	
	//click account link
	public void clickMyAccountLink() {
		highlightElementClass.highlightElement(driver, myAccount);
		myAccount.click();
	}
	
	//logout
	public void clickLogoutLink() {
		highlightElementClass.highlightElement(driver, logout);
		logout.click();
	}
	
	public void setSearchInputData(String search) {
		highlightElementClass.highlightElement(driver, searchInput);
		searchInput.sendKeys(search);
	}
	
	//click cart button
	public void clickCartButton() {
		cartButton.click();
	}
	
	//get cart count 
	public String getCartCount() {
		return cartCount.getText();
	}
	
	//click remove item icon
	public void clickRemoveItem() {
		removeItem.click();
	}
	
	//click okay button in the modal to delete cart item
	public void clickModalOk() {
		modalOkButton.click();
	}
	
	//check if cart box contains empty message
	public boolean isCartEmpty() {
		highlightElementClass.highlightElement(driver, cartEmptyMessage);
		return (wait.until(ExpectedConditions.visibilityOf(cartEmptyMessage)).isDisplayed());
	}
	
	//click minicart close button in the cart box
	public void clickminiCartClose() {
		minicartClose.click();
	}
}
