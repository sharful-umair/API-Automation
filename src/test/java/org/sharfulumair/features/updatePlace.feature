Feature: Validating Update Place APIs

  @UpdatePlace
  Scenario: Update Place functionality
    Given I update the payload "UpdatePlace.json" with the following values
      | key          | value                        |
      | address      | Updated Name From Data Table |
    When User calls "UpdatePlaceAPI" with "Put" http request
    Then The API calls got success with status code 200