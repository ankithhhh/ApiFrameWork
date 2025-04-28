Feature: User API DELETE Request

  @delete
  Scenario Outline: Verify DELETE request using inline data
    Given User loads the base URL from config
    And User sets up the User API request
    When User sends a DELETE request to "<endpoint>" with id "<id>"
    Then the response status code should be <ExpectedStatusCode>
    And the response time should be less than <ExpectedResponseTime> ms
    And User logs the response body

    Examples:
      | endpoint    | id | ExpectedStatusCode | ExpectedResponseTime |
      | /users/{id} |  1 |                200 |                 2000 |
      | /users/{id} |  2 |                404 |                 2000 |
      | /users/{id} |  3 |                200 |                 2000 |
