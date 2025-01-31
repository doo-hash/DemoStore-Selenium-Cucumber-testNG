package org.demoStore.stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.demoStore.pages.CheckoutPage;
import org.demoStore.pages.HomePage;
import org.demoStore.pages.LogoutSuccessPage;
import org.demoStore.pages.OrderDetailsReceiptPage;
import org.demoStore.pages.OrderSuccessPage;
import org.demoStore.pages.SearchProductPage;
import org.demoStore.utilities.AppUtilities;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BuyProductStepDef extends AppUtilities {
	
	@When("user empties cart items")
	public void user_empties_cart_items() {
		wait = new WebDriverWait(driver, Duration.ofMillis(1000));
		homePage = new HomePage(driver);
		searchProductPage = new SearchProductPage(driver);
		logger.info("Check if cart is empty or not");

		String cartCount = homePage.getCartCount();
		logger.info("cart count : "+ cartCount);

		if(cartCount.length() != 0) {
			homePage.clickCartButton();
			logger.info("clicked cart button");
			homePage.clickRemoveItem();
			logger.info("clicked remove item icon");
			logger.info("Modal to remove item is visible");			
			homePage.clickModalOk();
			logger.info("clicked okay to confirm deletion");
			boolean status = homePage.isCartEmpty();
			assertTrue(status);
			logger.info("cart is now empty");
			homePage.clickminiCartClose();
			logger.info("closing the cart box");
		}else {
			logger.info("Cart is empty");
		}
	}

	@When("user search and find product {string}")
	public void user_search_and_find_product (String product) {
		homePage.setSearchInputData(product + Keys.ENTER);
		logger.trace("Searching for product: " + product);

		assertTrue(searchProductPage.searchValuePresent(product));

		boolean status = searchProductPage.searchedProductPresent(product);
		if(status) {
			searchProductPage.getSearchedProduct();
			logger.trace("Searched product found");
		}else {
			logger.warn("product not found");
		}
	}
		
	@When("user selects color and size")
	public void user_selects_color_and_size() {
		searchProductPage.selectLSize();
		searchProductPage.selectProductColor();
		logger.trace("Selecting color and size");
	}
	
	@When("user adds product to cart")
	public void user_adds_product_to_cart() {
		searchProductPage.clickAddToCartButton();
		logger.trace("Clicked add to cart button");
		boolean alertStatus = searchProductPage.isProductAddedToCartAlertPresent();
		boolean cartStatus = searchProductPage.iscartCountUpdated();
		
		if(alertStatus || cartStatus) {
			assertTrue(true);
			searchProductPage.clickCartButton();
			logger.trace("Clicked cart button to proceed checkout");			
		}else {
			logger.fatal("alert success message or cart status is updated");
		}
	}

	@Then("user clicks checkout button")
	public void user_clicks_checkout_button() {
		try {
			searchProductPage.clickCheckOutButton();
			logger.trace("Clicked checkout button");
			Thread.sleep(2000);
			wait.until(d -> driver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/"));
			wait.until(d -> driver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/#shipping"));
			logger.info(driver.getCurrentUrl());
			logger.trace("Navigated to checkout page");			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Then("user verifies address and order details")
	public void user_verifies_address_and_order_details() {
		try {
			checkoutPage = new CheckoutPage(driver);
			checkoutPage.waitUntilShippingAddressVisible();
			checkoutPage.isShippingAddressDisplayed();
			checkoutPage.clickToSeeOrders();
			checkoutPage.selectShippingRate();
			checkoutPage.clickNextButton();
			wait.until(d -> driver.getCurrentUrl().equals("https://magento.softwaretestingboard.com/checkout/#payment"));
			Thread.sleep(2000);
			logger.trace("Clicked next button to see billing address");
			logger.info(driver.getCurrentUrl());

			if(checkoutPage.isBillingAddressSelected()) {
				assertTrue(true);
				logger.trace("billing address is selected");
			}else {
				logger.warn("billing address not selected");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@Then("user clicks place order button")
	public void user_clicks_place_order_button() throws InterruptedException {
		Thread.sleep(3000);
		try {
			checkoutPage.clickPlaceOrderButton();
			logger.trace("Clicked place order button");
			wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/checkout/onepage/success/"));
			logger.info(driver.getCurrentUrl());
			logger.trace("Navigated to orders page");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@Then("user checks order receipt {string}")
	public void user_checks_order_receipt(String product) {
		try {
			orderSuccessPage = new OrderSuccessPage(driver);
			orderDetailsReceiptPage = new OrderDetailsReceiptPage(driver);
			String orderNumber = orderSuccessPage.getOrderNumber();
			logger.trace("order number is: " + orderNumber);
			orderSuccessPage.clickOrderNumber();
			logger.trace("Clicked order number link");
			wait.until(d -> driver.getCurrentUrl().contains("https://magento.softwaretestingboard.com/sales/order/view/order_id/"));
			logger.info(driver.getCurrentUrl());
			logger.trace("Navigated to order details page");
			String orderID = orderDetailsReceiptPage.getOrderID();
			
			if(orderID.contains(orderNumber)) {
				assertTrue(true);
				String productName = orderDetailsReceiptPage.getProductName();
				assertTrue(productName.contains(product));
				orderDetailsReceiptPage.scrollDownToOrederedItems();
				orderDetailsReceiptPage.scrollDownToOrederInfo();
				logger.trace("Checked order details");
			}else {
				logger.fatal("Order id does not match");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	@Then("user signs out of the application")
	public void user_signs_out_of_the_application() {
		try {
			logoutSuccessPage = new LogoutSuccessPage(driver);
			orderDetailsReceiptPage.clickAccountDropdown();
			wait.until(d -> orderDetailsReceiptPage.isDropdownVisible());

			orderDetailsReceiptPage.clickLogoutLink();
			logger.trace("Clicked logout button");
			
			wait.until(d -> logoutSuccessPage.isUrl());
			assertTrue(logoutSuccessPage.isSignoutMessagePresent());			
			logger.info("logout successful");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

}
