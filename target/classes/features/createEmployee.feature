Feature: Create Employees

  @Scenario1
  Scenario Outline: post Employee valid data
    Given Create employee request data:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    When Send post request on /create
    Then Response status is 200 OK
    And The employee is returned with correct data
    And Get request on /employee for the user created above
    #additional assertion because of endpoint instability - frequent 429
    And Response status is 200 OK
    And The employee is returned with correct data
    Examples:
      | name         | salary | age |
      | Caesar Vance | 106450 | 21  |

  @Scenario2
  Scenario: post Employee missing parameters from body
    Given Create employee request data with missing parameters:
      | salary | 133 |
      | age    | 18  |
    When Send invalid post request on /create
#    Then Response status is 400 - negative scenarion not handled on mockapi

  @Scenario3
  Scenario: post Employee empty body
    Given Create empty request data:
    When Send invalid post request on /create
#    Then Response status is 400 - negative scenarion not handled on mockapi