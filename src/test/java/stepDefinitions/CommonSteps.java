package stepDefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import hooks.Hooks;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigReader;
import utils.ExtentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

public class CommonSteps {

    private final TestContext testContext;

    public CommonSteps(TestContext context) {
        this.testContext = context;
    }

    @Given("User loads the base URL from config")
    public void loadBaseUrlFromConfig() throws IOException {
        Hooks.logStepText("User loads the base URL from config"); // Log step text
        String baseUrl = ConfigReader.getBaseUrl();
        RestAssured.baseURI = baseUrl;
        ExtentService.log(Status.INFO, "Base URI set to: " + RestAssured.baseURI);
        System.out.println("Base URI set to: " + RestAssured.baseURI);
    }

    @And("User sets up the User API request")
    public void user_sets_up_request() {
        Hooks.logStepText("User sets up the User API request"); // Log step text
        testContext.setRequest(RestAssured.given().contentType(ContentType.JSON));
        ExtentService.log(Status.INFO, "Request setup with Content-Type: application/json");
        System.out.println("Request setup with Content-Type: application/json");
    }
    
    @Then("the response status code should be {int}")
    public void validate_status_code(int expectedStatusCode) {
        Hooks.logStepText("the response status code should be " + expectedStatusCode); // Log step text
        Response response = testContext.getResponse();
        if (response == null) {
            ExtentService.log(Status.FAIL, "Response is null, cannot assert status code");
            throw new AssertionError("Response is null");
        }

        int actual = response.getStatusCode();
        try {
            assertEquals(expectedStatusCode, actual);
            ExtentService.log(Status.PASS, "Validated status code: " + actual);
        } catch (AssertionError e) {
            ExtentService.log(Status.FAIL, "Expected status code: " + expectedStatusCode + " but got: " + actual);
            throw e;
        }
    }

    @Then("the response time should be less than {int} ms")
    public void validate_response_time(int maxResponseTime) {
        Hooks.logStepText("the response time should be less than " + maxResponseTime + " ms"); // Log step text
        long actualTime = testContext.getResponseTime();
        try {
            if (actualTime > maxResponseTime) {
                throw new AssertionError("Response time exceeded: " + actualTime + " ms");
            }
            ExtentService.log(Status.PASS, "Response time within limits: " + actualTime + " ms");
        } catch (AssertionError e) {
            ExtentService.log(Status.FAIL, e.getMessage());
            throw e;
        }
    }

    @And("User logs the response body")
    public void user_logs_response_body() {
        Hooks.logStepText("User logs the response body"); // Log step text
        String responseBody = testContext.getResponse().asPrettyString();
        ExtentService.log(Status.INFO, "Final Response:\n" + responseBody);
        System.out.println("Final Response:\n" + responseBody);
    }

}
