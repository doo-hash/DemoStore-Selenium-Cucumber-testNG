@all
Feature: User checks his/her orders in demo store application
user wants to check all his orders in this store

Background:
    Given user opens the application	
    And user navigates to login page

  @myOrders @smoke @regression
  Scenario Outline: User checks orders in the store
  	When user login to the application with "johndrew@gmail.com" and "Password123"
  	And user was redirected back to home page
  	And user clicks on my account
  	And user clicks on my orders
  	Then user checks his orders
  	And user logs out
