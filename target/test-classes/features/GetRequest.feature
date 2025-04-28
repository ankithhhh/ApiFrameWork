Feature: Get user by ID
	
	@get
  Scenario Outline: Verify GET request with parameters
    Given User loads the base URL from config
    And User sets up the User API request
    When User sends a GET request to "<endpoint>" with id "<id>"
    Then the response status code should be <ExpectedStatusCode>
    And the response time should be less than <ExpectedResponseTime> ms
    And User logs the response body

    Examples: 
      | endpoint    | id | ExpectedStatusCode | ExpectedResponseTime |
      | /users/{id} |  1 |                200 |                 1000 |
      | /users/{id} |  2 |                200 |                 1000 |
