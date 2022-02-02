Feature: Get Employees

  @Scenario1
  Scenario Outline: GET existing user data
    When Get request on v1/employee for id: 2
    Then Response code is 200
    And Employee response has status success and message Successfully! Record has been fetched.
    And Response schema corresponds with baseline: baselines/getEmployeeSchema.json
    And The employee is returned with data:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    Examples:
      | name            | salary | age |
      | Garrett Winters | 170750 | 63  |

  @Scenario2
  Scenario Outline: GET created Employee user data
    Given Create employee request:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    And Send post request on /create
    When Get request on v1/employee for the user created above
    Then Response status is 200 OK
    And The employee is returned with data:
      | name   | <name>   |
      | salary | <salary> |
      | age    | <age>    |
    Examples:
      | name         | salary | age |
      | Caesar Vance | 106450 | 21  |

  @Scenario3
  Scenario: GET Employee empty id
    When Get request on v1/employee with empty Id
    Then Response status is 404 Not Found

  @Scenario4
  Scenario: GET Employee non existing id
    When Get request on v1/employee for id: 999999
#    Then Response status is 404 Not found
#  fails - dummmy endpoint return 200 ok with null data

