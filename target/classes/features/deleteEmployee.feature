Feature: Delete Employee

  @Scenario1
  Scenario: delete valid employee
    When Delete employee request for id: 2
    Then Response status is 200 OK
    And The response returns message: Successfully! Record has been deleted
#    When Get request on v1/employee for id: 2
#    Then Response status is 404 Not found
  #commented because mockdelte does not delete entries

  @Scenario2
  Scenario: delete request with invalid id
    When Delete employee request for id: invalidID
#    Then Response status is 400 Bad Request

  @Scenario3
  Scenario: delete request for an id that does not exits
    When Delete employee request for id: 123123213
#    Then Response status is 404 Not found