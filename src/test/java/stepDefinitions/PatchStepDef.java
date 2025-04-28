package stepDefinitions;

import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import utils.ExtentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatchStepDef {

    private final TestContext testContext;

    public PatchStepDef(TestContext context) {
        this.testContext = context;
    }

    @And("User sets patch request body with new city {string}")
    public void user_sets_patch_body(String newCity) {
        JSONObject body = new JSONObject();
        body.put("city", newCity);

        testContext.setRequestBody(body.toString());
        testContext.getRequest().body(testContext.getRequestBody());

        ExtentService.log(Status.INFO, "Patch Request Body: " + testContext.getRequestBody());
        System.out.println("Patch Request Body: " + testContext.getRequestBody());
    }

    @When("User sends a PATCH request to {string} with id {string}")
    public void user_sends_patch_request(String endpointTemplate, String id) {
        String endpoint = endpointTemplate.replace("{id}", id);
        testContext.setEndpoint(endpoint);
        ExtentService.log(Status.INFO, "Sending PATCH request to endpoint: " + endpoint);

        Response response = testContext.getRequest().patch(endpoint);
        testContext.setResponse(response);
        testContext.setResponseTime(response.getTime());

        ExtentService.log(Status.INFO, "Response Status Code: " + response.getStatusCode());
        ExtentService.log(Status.INFO, "Response Body:\n" + response.asPrettyString());

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}
