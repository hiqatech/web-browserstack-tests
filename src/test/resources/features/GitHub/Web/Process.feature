#Author: hiqatech@gmail.com
#Keywords Summary : GitHub Tests

Feature: GitHub Home Tests

  @GitHubWebRegression
  Scenario:GitHub Web - I can run my process
    Given I navigate to the Home page
    And I "click" the "sing_in_button"
    And I "enter" "MyUserName" into the "email_field"
    And I "enter" "MyPassword" into the "password_field"
    When I "click" the "login_button"
    #And I receive the OTPNumber to my phoneNumber
    #And I "enter" "OTPNumber" into the "otp_number_field"
    #When I "click" the "otp_number_submit_button"
    Then I am on the "MyProfile" page
    And I should see the "my_profile_menu"
    And I "click" the "my_database_repository"
    And I "click" the "code_menu"
    #And I "click" the "my_cars_file"
    #And I "click" the "download_button"
    #And I send enter keys to the popup window
    #And I wait "2" sec/s for "download"
    #And I click the "browser_back_button"
    #And I "click" the "my_repository_name"
    And I "click" the "add_file_dropdown"
    And I "click" the "upload_file_option"
    And I upload the "MyCars1.xlsx" to the "choose_your_files_field"
    And I wait "2" sec/s for "upload"
    And I "click" the "commit_changes_button"
    And I wait "10" sec/s for "checkup"