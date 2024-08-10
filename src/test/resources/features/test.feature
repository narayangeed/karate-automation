@e2e
Feature: fetching User Details

  Background:
    * url karate.get('baseUrls')

  Scenario: testing the get call for User Details
    Given path '/users?page=2'
    When method GET
    Then status 200