Feature: Lambda Test overview

  Background: Website Homepage
    Given User is lambda test website

  Scenario: add new item
    When User add text
    And User click on add button
    Then User should see text added