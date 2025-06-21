#Author: hiqatech@gmail.com
#Keywords Summary : YouTube Search Tests

Feature: YouTube Search Tests

  Background:
    Given I start the "default" driver
    And I navigate to the "app_home" url
    When I am on the "Home" page

  @YouTubeWebSmoke
  Scenario:YouTubeWeb - I can find and play my music
    #Given I navigate to the Home page
    And I wait "10" sec/s for "signin window"
    And I "select" the "no_thanks_button"
    And I wait "10" sec/s for "agree window"
    And I switch to the "popup" content
    And I "select" the "i_agree_button"
    And I wait "10" sec/s for "default window"
    And I switch to the "default" content
    And I "type" "Dash Berlin - With you" into the "search_field"
    When I "click" the "search_submit"
    And I wait "4" sec/s for "search"
    And I am on the "Search" page
    Then I should see the "search_result_1st"
    And I takes screenshot as "search_and_play_evidence_1"
    Then I "select" the "search_result_1st"
    And I wait "10" sec/s for "play"
    And I "select" the "skip_ad_button"
    And I wait "24" sec/s for "play"

    #And I have fun :)
