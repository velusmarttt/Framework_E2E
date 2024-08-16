@tag
Feature: Error Validation
  I want to use this template for my future file

  @ErrorValidation
  Scenario Outline: Negative test of checking login
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on login page

    Examples:
      | name             | password  |
      | shetty@gmail.com | Iamking@0 |