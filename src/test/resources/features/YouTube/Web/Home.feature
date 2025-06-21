#Author: hiqatech@gmail.com
#Keywords Summary : YouTube Home Tests

Feature: YouTube Home Tests

  @YouTubeWebRegression
  Scenario:YouTubeWeb - I can find videos with Zurich
    Given I navigate to the Home page
    And I "select" the "no_thanks_button"
    And I wait "10" sec/s for "agree window"
    And I switch to the "popup" content
    And I "select" the "i_agree_button"
    And I wait "10" sec/s for "default window"
    And I takes screenshot as "search_evidence_1"

