package stepDefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ExtentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetStepDef {

    private final TestContext testContext;

    public GetStepDef(TestContext context) {
        this.testContext = context;
    }

    @When("User sends a GET request to {string} with id {string}")
    public void user_sends_get_request(String endpoint, String id) {
        String resolvedEndpoint = endpoint.replace("{id}", id);
        testContext.setEndpoint(resolvedEndpoint);

        ExtentService.log(Status.INFO, "Sending GET request to: " + resolvedEndpoint);
        Response response = testContext.getRequest().get(resolvedEndpoint);

        testContext.setResponse(response);
        testContext.setResponseTime(response.getTime());

        ExtentService.log(Status.INFO, "Response Code: " + response.getStatusCode());
        ExtentService.log(Status.INFO, "Response Body:<br><pre>" + response.getBody().asPrettyString() + "</pre>");
    }
}
