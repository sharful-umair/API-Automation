package org.sharfulumair.stepDefinations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.sharfulumair.resources.APIResources;
import org.sharfulumair.resources.TestDataBuild;
import org.sharfulumair.resources.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class StepDefination extends Utils {

    RequestSpecification request;
    ResponseSpecification responseSpecBuilder;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String placeId;
    static  JSONObject updatedJson;

    @Given("AddPlace payload {string} {string} {string} {int} {string} {string} {double} {double}")
    public void add_place_payload(String name, String language, String address, int accuracy, String number, String website, double lat, double lng) throws IOException {

        request = given().log().all()
                .spec(requestSpecification())
                .body(data.AddPlacePayload(name, language, address, accuracy, number, website, lat, lng));
    }

    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resourceAPI, String method) {

        if (request == null) {
            throw new NullPointerException("Request is not initialized.");
        }

        responseSpecBuilder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectHeader("Server", "Apache/2.4.52 (Ubuntu)")
                .build();

        APIResources apiName = APIResources.valueOf(resourceAPI);
        System.out.println(apiName.getResources());

        if(method.equalsIgnoreCase("Post"))
        {
            response = request.when().post(apiName.getResources());
        }
        else if(method.equalsIgnoreCase("Get"))
        {
            response = request.when().get(apiName.getResources());
        }
        else if(method.equalsIgnoreCase("Delete"))
        {
            response = request.when().delete(apiName.getResources());
        }
        else if(method.equalsIgnoreCase("Put"))
        {
            response = request.when().put(apiName.getResources());
        }

        if (response == null) {
            throw new NullPointerException("Response is null after API call.");
        }

//        System.out.println(response.asString());
    }

    @Then("The API calls got success with status code {int}")
    public void the_api_calls_got_success_with_status_code(int statusCode) {

        Assert.assertEquals(response.getStatusCode(), statusCode);
        System.out.println(statusCode);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String value) {

        Assert.assertEquals(getJsonPath(response, key), value);
    }

    @Then("Verify placeId created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        placeId = getJsonPath(response, "place_id");
        request = given().spec(requestSpecification()).queryParam("place_id", placeId);
        user_calls_with_http_request(resource, "Get");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

        request = given().spec(requestSpecification())
                .body(data.DeletePlacePayload(placeId));
    }


    @Given("I update the payload {string} with the following values")
    public void i_update_the_payload_with_the_following_values(String fileName, DataTable dataTable) throws IOException, ParseException {

//        String baseDir = "C:\\Users\\sharf\\IdeaProjects\\RestAssuredFramework\\src\\main\\java\\org\\sharfulumair\\payload";
        String baseDir = getGlobalValue("payloadBaseDir");
        // Construct the full file path
        String filePath = Paths.get(baseDir, fileName).toString();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("The specified file was not found: " + filePath);
        }

        // Read the original JSON file into a JSONObject
        JSONParser parser = new JSONParser();
        JSONObject originalJson;
        try (FileReader reader = new FileReader(file)) {
            originalJson = (JSONObject) parser.parse(reader);
        }

        // If the file is UpdatePlace.json, overwrite the place_id with the value from Hooks
        if (fileName.equals("UpdatePlace.json")) {
            if (placeId == null) {
                throw new NullPointerException("placeId is not initialized in the Hooks.");
            }
            originalJson.put("place_id", placeId);
        }

        // Convert DataTable to a JSONObject for updates
        JSONObject updates = new JSONObject();
        for (Map<String, String> row : dataTable.asMaps(String.class, String.class)) {
            String key = row.get("key");
            String value = row.get("value");
            updates.put(key, value);
        }

//        System.out.println("Updated JSON: " + originalJson.toJSONString());

        if(fileName.equalsIgnoreCase("UpdatePlace.json"))
        {
            updatedJson = mergeTwoJsonObjects(originalJson, updates);
        }
        else
        {
            updatedJson = updateJsonPayload(filePath, updates);
        }

        // Set the updated JSON as the request body
        request = given().spec(requestSpecification()).body(updatedJson.toJSONString());
    }

    @Then("the payload should be updated successfully and Print")
    public void the_payload_should_be_updated_successfully_and_print() {
        // Print the updated JSON
//        System.out.println(updatedJson.toJSONString());
        System.out.println("Payload Updated Successfully");
    }

    @When("User create request for the Get call")
    public void user_create_request_for_the_get_call() throws IOException {

        request = given().spec(requestSpecification()).queryParam("place_id", placeId);
    }
}
