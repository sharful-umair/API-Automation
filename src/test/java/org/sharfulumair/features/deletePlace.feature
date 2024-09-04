Feature: Validating Delete Place APIs

  @DeletePlace
  Scenario:Delete Place functionality
    Given DeletePlace Payload
    When User calls "DeletePlaceAPI" with "Delete" http request
    Then The API calls got success with status code 200
    And "status" in response body is "OK"
