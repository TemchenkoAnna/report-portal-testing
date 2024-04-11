Feature: Prevent duplicate dashboard
  As a user
  I should not be able to create a duplicate dashboard
  So that all dashboards are unique

  Background: : User creates dashboard
    Given I have a valid dashboard payload
    When I create a dashboard
    Then the dashboard should be created successfully

  Scenario: User can update dashboard
    Given I have the ID from newly created dashboard
    When I try to create a dashboard with the same details
    Then I should get an error message
