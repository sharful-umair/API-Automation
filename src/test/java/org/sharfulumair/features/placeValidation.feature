Feature: Validating Place APIs

  @AddPlace @GetPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given AddPlace payload "<name>" "<language>" "<address>" <accuracy> "<number>" "<website>" <lat> <lng>
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API calls got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify placeId created maps to "<name>" using "GetPlaceAPI"

    Examples:
    | name                | language  | address                   | accuracy | number             | website           | lat        | lng       |
    | Frontline house One | French    | 29, side layout, cohen 09 | 50       | (+91) 983 893 3937 | http://google.com | -38.383494 | 33.427362 |

    @DeletePlace
    Scenario:Delete Place functionality
      Given DeletePlace Payload
      When User calls "DeletePlaceAPI" with "Delete" http request
      Then The API calls got success with status code 200
      And "status" in response body is "OK"