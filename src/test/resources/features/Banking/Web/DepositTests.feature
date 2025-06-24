#Author: hiqatech@gmail.com
#Keywords Summary : Banking Web Tests

Feature: Banking Web Deposit Tests

  Background:
    Given I setup the "BankingWeb" product
    And I navigate to the Home page
    And I login with the "Ron Weasly" user
    And I navigate to the "Deposit" page

  @BankingWebSmoke
  Scenario:BankingWeb - I can deposit 100
    And I "enter" "100" into the "amount_field"
    And I "click" the "deposit_submit"
    And I should see the "deposit_successful"
    And The "balance" element "text" should be "100"
    And I "click" the "transactions_button"

    And I am on the "Transactions" page
    And The "transaction1_amount" element "text" should be "100"
    And I "click" the "reset_button"
    And I "click" the "back_button"

    And I am on the "UserHome" page
    And The "balance" element "text" should be "0"
    And I "click" the "logout_button"

