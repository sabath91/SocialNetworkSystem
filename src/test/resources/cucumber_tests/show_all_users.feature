Feature: It should get all users?

  Scenario: return list of all users
    Given HTTP GET request without headers
    When I should ask service for list of users
    Then I will return list of users in json format