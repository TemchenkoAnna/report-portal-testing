Feature: Delete dashboard
  As a user
  I want to delete a dashboard
  So that I can remove the dashboard

  Background: : User creates dashboard
    Given I have a valid dashboard payload
    When I create a dashboard
    Then the dashboard should be created successfully

  Scenario: User can update dashboard
    Given I have the ID from newly created dashboard
    When I delete the dashboard
    Then the dashboard should be deleted successfully
