Feature: Update dashboard
  As a user I want to update a dashboard
  So that I can change the dashboard details

  Background: : User creates dashboard
    Given I have a valid dashboard payload
    When I create a dashboard
    Then the dashboard should be created successfully

  Scenario: User can update dashboard
    Given I have the ID from newly created dashboard
    When I update the dashboard
    Then the dashboard should be updated successfully
