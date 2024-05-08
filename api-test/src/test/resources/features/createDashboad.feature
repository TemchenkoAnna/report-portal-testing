Feature: Dashboard creation
  As a user I want to create a dashboard
  So that I can manage my data

  Scenario: User can create dashboard
    Given I have a valid dashboard payload
    When I create a dashboard
    Then the dashboard should be created successfully