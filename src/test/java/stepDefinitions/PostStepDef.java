package stepDefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ExtentService;
import utils.ConfigReader;
import hooks.Hooks;  // Import Hooks class to log steps

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostStepDef {

    private final TestContext testContext;

    public PostStepDef(TestContext context) {
        this.testContext = context;
    }

    @And("User sets request body with id {string}, name {string}, city {string}, and age {int}")
    public void user_sets_request_body(String id, String name, String city, int age) {
        Hooks.logStepText("User sets request body with id " + id + ", name " + name + ", city " + city + ", and age " + age); // Log step text
        JSONObject body = new JSONObject();
        body.put("id", id);
        body.put("name", name);
        body.put("city", city);
        body.put("age", age);

        testContext.setRequestBody(body.toString());
        testContext.getRequest().body(testContext.getRequestBody());

        ExtentService.log(Status.INFO, "Request Body: " + testContext.getRequestBody());
        System.out.println("Request Body: " + testContext.getRequestBody());
    }

    @When("User sends a POST request to {string}")
    public void user_sends_post_request(String endpoint) {
        Hooks.logStepText("User sends a POST request to " + endpoint); // Log step text
        testContext.setEndpoint(endpoint);
        ExtentService.log(Status.INFO, "Sending POST request to endpoint: " + endpoint);

        Response response = testContext.getRequest().post(endpoint);
        testContext.setResponse(response);
        testContext.setResponseTime(response.getTime());

        ExtentService.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
        ExtentService.log(Status.INFO, "Response Body:\n" + response.asPrettyString());

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}