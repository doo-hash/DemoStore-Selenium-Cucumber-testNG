@all
Feature: User searches for a product in demo store application

Background:
    Given user opens the application	
    And user navigates to login page

  @search @smoke @regression
  Scenario Outline: User searches a product
  	When user login to the application with "johndrew@gmail.com" and "Password123"
  	And user was redirected back to home page
    When user enters product name "<product>"
    And user finds searched product "<product>"
    And user clicks product name link
    Then user checks product details
    And user logs out of the application

	Examples:
	| product     |
  |  duffle bag |