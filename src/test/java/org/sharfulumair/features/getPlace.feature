Feature: Validating Get Place APIs

  @GetPlace
  Scenario: Get Place functionality
    When User create request for the Get call
    When User calls "GetPlaceAPI" with "Get" http request
    Then The API calls got success with status code 200