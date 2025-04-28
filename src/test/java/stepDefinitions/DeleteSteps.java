package stepDefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExtentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteSteps {

    private final TestContext testContext;

    public DeleteSteps(TestContext context) {
        this.testContext = context;
    }

    @When("User sends a DELETE request to {string} with id {string}")
    public void user_sends_delete_request_with_id(String endpoint, String id) {
        String formattedEndpoint = endpoint.replace("{id}", id); // Replacing {id} with the actual id from the scenario
        testContext.setEndpoint(formattedEndpoint);

        ExtentService.log(Status.INFO, "Sending DELETE request to endpoint: " + formattedEndpoint);
        Response response = testContext.getRequest().delete(formattedEndpoint);
        testContext.setResponse(response);
        testContext.setResponseTime(response.getTime());

        ExtentService.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
        ExtentService.log(Status.INFO, "Response Body:\n" + response.asPrettyString());

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}
