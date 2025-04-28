Feature: User API PATCH Request to update city

  @patch
  Scenario Outline: Verify PATCH request for updating city
    Given User loads the base URL from config
    And User sets up the User API request
    And User sets patch request body with new city "<city>"
    When User sends a PATCH request to "<endpoint>" with id "<id>"
    Then the response status code should be <ExpectedStatusCode>
    And the response time should be less than <ExpectedResponseTime> ms
    And User logs the response body

    Examples:
      | id | city     | endpoint     | ExpectedStatusCode | ExpectedResponseTime |
      | 1  | bangalore | /users/{id} | 200                | 2000                 |
      | 2  | mumbai    | /users/{id} | 200                | 2000                 |
