
@tag
Feature: Purchase the Order form Ecommerse Website
  I want to use this template for my feature file

	Background:
	Given I Landed on Ecommerce Page
	
  @Regression
  Scenario Outline: positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | name  								| password 			| productName				|
      | abhishek@gmail.com 		| Abhishek@123 	| ADIDAS ORIGINAL 	|