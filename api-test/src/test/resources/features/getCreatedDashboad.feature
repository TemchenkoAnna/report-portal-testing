Feature: Get dashboard by ID
  As a user I want to get a dashboard by ID
  So that I can view the dashboard details

  Background: : User creates dashboard
    Given I have a valid dashboard payload
    When I create a dashboard
    Then the dashboard should be created successfully

    Scenario:
      Given I have the ID from newly created dashboard
      When I request the dashboard by its ID
      Then I should get the dashboard details
