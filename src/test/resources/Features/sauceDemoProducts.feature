Feature: Login page Automation of saucedemo App

  Scenario Outline: Check login is successful with valid credentials
    Given User is on login page
    When User enters valid "<username>" and "<password>"
    And Clicks on login Button
    Then User should be "<loginStatus>"
    Then close the browser

    Examples: 
      | username        | password     | loginStatus       |
      | standard_user   | secret_sauce | navigated_to_home |
      | visual_user     | secret_sauce | navigated_to_home |
      
   Scenario Outline: Check login is unsuccessful with valid credentials
    Given User is on login page
    When User enters valid "<username>" and "<password>"
    And Clicks on login Button
    Then User should be "<loginStatus>"
    Then close the browser

    Examples: 
      | username        | password     | loginStatus       |
      | locked_out_user | secret_sauce | not_navigated     |
      

  Scenario Outline: Sort products on the Home Page
    Given User is logged in with username "standard_user" and password "secret_sauce"
    Given User navigates to the Home Page
    When sorting products by "<filter>"
    Then Products sort in "<filter>"
    Then close the browser

    Examples: 
      | filter              |
      | Price (low to high) |
      | Price (high to low) |
      | Name (Z to A)       |


  Scenario Outline: Add Product to the cart - Checkout - Logout
    Given User is logged in with username "standard_user" and password "secret_sauce"
    Given User navigates to the Home Page
    When add "Sauce Labs Bike Light" to the cart
    Then Product added to the cart
    Then Go to checkout page
    Given user navigate to checkout page
    And click checkout button
    And enter info "<firstName>", "<lastName>" and "<postalCode>"
    And click continue button
    And click finish button
    Then Order is successfully placed
    Given User navigates to the Home Page
    When click menu bar
    And click logout button
    Then user should be logged out successfully

    Examples: 
      | firstName | lastName | postalCode |
      | Muhammad  | Haseeb   |      12345 |
