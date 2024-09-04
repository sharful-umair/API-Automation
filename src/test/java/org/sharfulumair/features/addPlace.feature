Feature: Update JSON Payload

  @NewAddPlace
  Scenario: Verify AddPlace API
    Given I update the payload "AddPlace.json" with the follosing values
      | key          | value                        |
      | name         | New Name From Data Table     |
      | address      | New Address From Data Table  |
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API calls got success with status code 200
    And the payload should be updated successfully and Print

