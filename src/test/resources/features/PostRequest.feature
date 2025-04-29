Feature: User API POST Request using feature file data

  @post
  Scenario Outline: Verify POST request using inline data
    Given User loads the base URL from config
    And User sets up the User API request
    And User sets request body with id "<id>", name "<name>", city "<city>", and age <age>
    When User sends a POST request to "<endpoint>"
    Then the response status code should be <ExpectedStatusCode>
    And the response time should be less than <ExpectedResponseTime> ms
    And User logs the response body

    Examples: 
      | id | name   | city   | age | endpoint | ExpectedStatusCode | ExpectedResponseTime |
      |  1 | sanath | puttur |  25 | /users   |                201 |                 2000 |
      |  2 | rahul  | delhi  |  30 | /users   |                201 |                 2000 |
      |  3 | ravi   | patna  |  40 | /users   |                401 |                 2000 |
