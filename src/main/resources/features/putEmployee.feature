Feature: Put Employee

  @Scenario1
  Scenario Outline: PUT Employee endpoint
    Given Create employee request data:
      | name   | <name1>   |
      | salary | <salary1> |
      | age    | <age1>    |
    And Send post request on /create
    When Create employee request data:
      | name   | <name2>   |
      | salary | <salary2> |
      | age    | <age2>    |
    And Put request on /update for the created resource
    Then Response status is 200 OK
    And The employee is returned with correct data
    And Get request on /employee for the user created above
        #additional assertion because of endpoint instability - frequenst 429
    And Response status is 200 OK
    And The response has the following data:
      | employee_name   | <name2>   |
      | employee_salary | <salary2> |
      | employee_age    | <age2>    |
    Examples:
      | name1     | salary1 | age1 | name2        | salary2 | age2 |
      | Joe Pesci | 1       | 23   | Caesar Vance | 106450  | 21   |

  @Scenario2
  Scenario: Put request with missing data
    Given Create employee request data with missing parameters:
      | salary | 133 |
      | age    | 18  |
    When Put request on /update for the created resource
#    Then Response status is 400 Bad Request

  @Scenario3
  Scenario: Put request with empty bodyu
    Given Create empty request data:
    When Put request on /update for the created resource
#    Then Response status is 400 Bad Request
