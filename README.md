# API-Automation
API automation framework using Rest Assured, Cucumber, and Java.

# RestAssured Framework for AddPlace API

## Overview
This project is a RestAssured framework designed to handle the AddPlace API. It uses Cucumber for BDD, allowing you to validate the AddPlace API by updating a JSON payload with specific values from a Cucumber data table.

## Project Structure

## IDE Project Structure Image
<img width="955" alt="image" src="https://github.com/user-attachments/assets/8bfc1bb3-e1ef-4ae8-bc3c-0ac228d4d122">
<img width="955" alt="image" src="https://github.com/user-attachments/assets/ee9b48de-7c57-43d8-b189-913e970f413e">

### 1. Feature File (`AddPlace.feature`)
- **Scenario**: Validates the AddPlace API by updating a JSON payload (`AddPlace.json`) with specific values from a Cucumber data table.
- **Steps**:
  - `Given I update the payload "AddPlace.json" with the following values`: Updates the JSON payload with specified keys and values from the data table.
  - `When User calls "AddPlaceAPI" with "Post" http request`: Calls the AddPlaceAPI endpoint using a POST request.
  - `Then The API calls got success with status code 200`: Validates the response status code.
  - `And the payload should be updated successfully and Print`: Confirms that the payload was updated correctly.
    

### 2. Test Runner (`TestRunner.java`)
- **Cucumber Options**:
  - `features`: Points to the location of your feature files.
  - `glue`: Indicates the package where the step definitions are located.
  - `plugin`: Generates a JSON report for the test results.

### 3. Step Definitions (`StepDefination.java`)
- **Key Methods**:
  - `@Given("I update the payload {string} with the following values")`: Loads the specified JSON file, updates it with values from the data table, and sets it as the request body.
  - `@When("User calls {string} with {string} http request")`: Executes the API call based on the specified method (e.g., POST, GET).
  - `@Then("The API calls got success with status code {int}")`: Verifies the response status code.
  - `@Then("the payload should be updated successfully and Print")`: Prints the updated JSON payload to confirm the changes.

### 4. Utilities (`Utils.java`)
- **Methods**:
  - `updateJsonPayload`: Merges the original JSON with updates from the data table without altering the original file.
  - `mergeJsonObjects`: Handles recursive merging of nested JSON objects.
  - `requestSpecification`: Creates a request specification with logging and other configurations.

### 5. Test Data Builder (`TestDataBuild.java`)
- Builds POJO objects for the AddPlace and DeletePlace requests.

### 6. Enum for API Resources (`APIResources.java`)
- Stores the endpoints for different APIs as enum constants, making it easier to manage and update API endpoints.

### 7. Global Properties (`global.properties`)
- Stores configuration values such as `baseUrl`, `key`, and `payloadBaseDir`, which are loaded using the `getGlobalValue` method.

### 8. Payload JSON File (`AddPlace.json`)
- Example payload file located in the specified `payloadBaseDir` directory.

## Execution Flow
1. The feature file triggers the step definitions.
2. The step definitions utilize the utilities and POJOs to interact with the API.
3. The responses are validated as per the defined steps.

## Debugging
If you encounter any issues, you can further debug by adding `System.out.println()` statements or by stepping through the code with a debugger to inspect the values at different points in the execution.

## Report
<img width="955" alt="image" src="https://github.com/user-attachments/assets/7aa2f971-ce4f-467d-be1f-d6e52c4d73c8">

