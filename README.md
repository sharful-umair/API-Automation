# API-Automation
API automation framework using Rest Assured, Cucumber, and Java.
This setup is almost robust and allows for easy extension and maintenance. 
You can add new features, scenarios, or even APIs without significant changes to the underlying structure.

# RestAssured Framework for API Automation

## Overview
This project is a RestAssured framework designed to handle the API Automation. 
It uses Cucumber for BDD, allowing you to automated the API by updating a JSON payload with specific values from a Cucumber data table and pojo class, create request and verify the response.

## Workflow
Feature Execution: You execute tests using the Test Runner, which triggers Cucumber to read feature files.
Step Execution: Each step in the feature files is matched to a step definition, where the actual code is executed.
API Request: The framework builds and sends the API request using Rest Assured.
Response Validation: The response is validated against expected outcomes, such as status codes and payload content.
Logging: Requests and responses are logged to a file for later inspection.

![diagram-export-9-4-2024-11_16_14-PM](https://github.com/user-attachments/assets/f881e812-0e6a-402c-9b66-c6c6ab393f3f)


## Tech Stack Used
Here are the key technologies used in the framework:

1. **Java**: The core programming language.
2. **Maven**: Build automation tool used for managing project dependencies and building the project.
3. **Rest Assured**: For API testing and validation.
4. **Hamcrest**: For making assertions in tests.
5. **Jackson Databind**: For parsing and processing JSON data.
6. **Cucumber**: For BDD (Behavior Driven Development), including `cucumber-java` and `cucumber-junit`.
7. **JUnit**: For running unit tests.
8. **Groovy**: For scripting within the Java environment.
9. **Maven Surefire Plugin**: For running tests during the build process.
10. **JSON (org.json and json-simple)**: Libraries for working with JSON data.
11. **Maven Cucumber Reporting**: For generating Cucumber reports after test execution. 

These technologies together create a comprehensive framework for API automation testing with BDD capabilities.

## Project Structure

## IDE Project Structure Image
<img width="959" alt="image" src="https://github.com/user-attachments/assets/3c259929-9291-4572-986a-701e5a2cb725">
<img width="959" alt="image" src="https://github.com/user-attachments/assets/91288e8e-9385-4fde-9b0a-ec667eef3c0b">

### 1. Feature File (`AddPlace.feature`)
- **Scenario**: Validates the AddPlace API by updating a JSON payload (`AddPlace.json`) with specific values from a Cucumber data table.
- **Steps**:
  - `Given I update the payload "AddPlace.json" with the following values`: Updates the JSON payload with specified keys and values from the data table.
  - `When User calls "AddPlaceAPI" with "Post" http request`: Calls the AddPlaceAPI endpoint using a POST request.
  - `Then The API calls got success with status code 200`: Validates the response status code.
  - `And the payload should be updated successfully and Print`: Confirms that the payload was updated correctly.
 <img width="955" alt="image" src="https://github.com/user-attachments/assets/41e84134-8143-480b-8697-958de17703d6">

    

### 2. Test Runner (`TestRunner.java`)
- **Cucumber Options**:
  - `features`: Points to the location of your feature files.
  - `glue`: Indicates the package where the step definitions are located.
  - `plugin`: Generates a JSON report for the test results.
 <img width="959" alt="image" src="https://github.com/user-attachments/assets/47be172b-f293-406b-a25d-64ccb2f3a3c3">


### 3. Step Definitions (`StepDefination.java`)
- **Key Methods**:
  - `@Given("I update the payload {string} with the following values")`: Loads the specified JSON file, updates it with values from the data table, and sets it as the request body.
  - `@When("User calls {string} with {string} http request")`: Executes the API call based on the specified method (e.g., POST, GET).
  - `@Then("The API calls got success with status code {int}")`: Verifies the response status code.
  - `@Then("the payload should be updated successfully and Print")`: Prints the updated JSON payload to confirm the changes.
    <img width="959" alt="image" src="https://github.com/user-attachments/assets/04ce076a-c5e8-406b-8e0b-4e5a8c1ad558">


### 4. Utilities (`Utils.java`)
- **Methods**:
  - `updateJsonPayload`: Merges the original JSON with updates from the data table without altering the original file.
  - `mergeJsonObjects`: Handles recursive merging of nested JSON objects.
  - `requestSpecification`: Creates a request specification with logging and other configurations.
    <img width="956" alt="image" src="https://github.com/user-attachments/assets/5ba3a801-b8b8-47b8-9ae7-86bca8be0285">


### 5. Test Data Builder (`TestDataBuild.java`)
- Builds POJO objects for the AddPlace and DeletePlace requests.
  <img width="959" alt="image" src="https://github.com/user-attachments/assets/fb21269d-eda9-4619-888b-ce9a59528165">


### 6. Enum for API Resources (`APIResources.java`)
- Stores the endpoints for different APIs as enum constants, making it easier to manage and update API endpoints.
  <img width="959" alt="image" src="https://github.com/user-attachments/assets/757609cd-99b5-426e-ae22-cc553a05aecd">


### 7. Global Properties (`global.properties`)
- Stores configuration values such as `baseUrl`, `key`, and `payloadBaseDir`, which are loaded using the `getGlobalValue` method.
  <img width="957" alt="image" src="https://github.com/user-attachments/assets/c2988223-f43a-4318-ac87-345f4185693e">


### 8. Payload JSON File (`AddPlace.json`)
- Example payload file located in the specified `payloadBaseDir` directory.
  <img width="957" alt="image" src="https://github.com/user-attachments/assets/3b247ab8-cd4d-410a-8b61-1d3cd4c42541">

### 9. Logging
  - File to log all request and response
    <img width="953" alt="image" src="https://github.com/user-attachments/assets/ec7bae9c-a446-4ff6-ada4-ffb43e768d8a">



## Execution Flow
1. The feature file triggers the step definitions.
2. The step definitions utilize the utilities and POJOs to interact with the API.
3. The responses are validated as per the defined steps.

## Debugging
If you encounter any issues, you can further debug by adding `System.out.println()` statements or by stepping through the code with a debugger to inspect the values at different points in the execution.

## Cucumber HTML Report
<img width="955" alt="image" src="https://github.com/user-attachments/assets/7aa2f971-ce4f-467d-be1f-d6e52c4d73c8">

## CI CD
Coming soon

