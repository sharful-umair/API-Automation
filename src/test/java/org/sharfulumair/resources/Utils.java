package org.sharfulumair.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class Utils {

    public static RequestSpecification requestSpecBuilder;

    public RequestSpecification requestSpecification() throws IOException {

        if(requestSpecBuilder == null)
        {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpecBuilder = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", getGlobalValue("key"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpecBuilder;
        }
        return requestSpecBuilder;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream("C://Users//sharf//IdeaProjects//RestAssuredFramework//src//test//java//org//sharfulumair//resources//global.properties");
        properties.load(fis);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key)
    {
        String strResp = response.asString();
        JsonPath jsonPath = new JsonPath(strResp);
        return jsonPath.get(key).toString();
    }

    public JSONObject updateJsonPayload(String filePath, JSONObject updates) throws IOException, ParseException {
        // Read the original JSON file
        JSONParser parser = new JSONParser();
        JSONObject originalJson;

        try (FileReader reader = new FileReader(filePath)) {
            originalJson = (JSONObject) parser.parse(reader);
        }

        // Debugging: Print the JSON objects
//        System.out.println("Original JSON: " + originalJson.toJSONString());
//        System.out.println("Updates JSON: " + updates.toJSONString());

        // Ensure the updates object is not null
        if (updates == null) {
            throw new IllegalArgumentException("Updates JSON cannot be null");
        }

        // Recursively merge updates into the original JSON
        mergeJsonObjects(originalJson, updates);

        // Return the updated JSON without modifying the original file
        return originalJson;
    }

    public void mergeJsonObjects(JSONObject original, JSONObject updates) {
        if (original == null || updates == null) {
            throw new IllegalArgumentException("Original or Updates JSON cannot be null");
        }

        for (Object key : updates.keySet()) {
            Object updateValue = updates.get(key);
            if (updateValue instanceof JSONObject) {
                Object originalValue = original.get(key);
                if (originalValue instanceof JSONObject) {
                    mergeJsonObjects((JSONObject) originalValue, (JSONObject) updateValue);
                } else {
                    original.put(key, updateValue);
                }
            } else {
                original.put(key, updateValue);
            }
        }
    }

    public JSONObject mergeTwoJsonObjects(JSONObject original, JSONObject updates) {
        if (original == null || updates == null) {
            throw new IllegalArgumentException("Original or Updates JSON cannot be null");
        }

        for (Object key : updates.keySet()) {
            Object updateValue = updates.get(key);
            if (updateValue instanceof JSONObject) {
                Object originalValue = original.get(key);
                if (originalValue instanceof JSONObject) {
                    mergeTwoJsonObjects((JSONObject) originalValue, (JSONObject) updateValue);
                } else {
                    original.put(key, updateValue);
                }
            } else {
                original.put(key, updateValue);
            }
        }
        return original;
    }
}
