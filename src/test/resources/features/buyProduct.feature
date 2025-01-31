@all
Feature: User buys a product in demo store application

Background:
		Given user opens the application	
    And user navigates to login page
  	When user login to the application with "johndrew@gmail.com" and "Password123"
  	And user was redirected back to home page

  @product @smoke @regression
  Scenario: User buys a product
  	When user empties cart items
    When user search and find product "Selene Yoga Hoodie"
		And user selects color and size
		And user adds product to cart
		Then user clicks checkout button
		And user verifies address and order details
		And user clicks place order button
		Then user checks order receipt "Selene Yoga Hoodie" 
		And user signs out of the application