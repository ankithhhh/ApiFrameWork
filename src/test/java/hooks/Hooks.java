package hooks;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import context.TestContext;
import io.cucumber.java.*;
import io.restassured.response.Response;
import utils.EmailUtil;
import utils.ExtentManager;

public class Hooks {

    private static ExtentTest test;
    private final TestContext context;
    private static String currentStepText = "Step not captured";

    public Hooks(TestContext context) {
        this.context = context;
    }

    // This method allows logging step text to be captured before each step
    public static void logStepText(String stepText) {
        currentStepText = stepText;
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        test = ExtentManager.getReporter().createTest(scenario.getName());
        test.log(Status.INFO, "Starting scenario: " + scenario.getName());
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // Check if the step failed by comparing the step's status with FAILED
        if (scenario.getStatus().toString().equalsIgnoreCase("FAILED")) {
            // Log failure for the current step
            test.log(Status.FAIL, "❌ Step Failed: " + currentStepText);
        } else {
            // Log success for the current step
            test.log(Status.PASS, "✅ Step Passed: " + currentStepText);
        }
    }

    @After
    public void afterScenario(Scenario scenario) {
        Response response = context.getResponse();
        String requestBody = context.getRequestBody();
        String endpoint = context.getEndpoint();
        long responseTime = context.getResponseTime();

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "❌ Scenario failed");

            if (endpoint != null)
                test.log(Status.INFO, "Endpoint: " + endpoint);
            if (requestBody != null)
            	test.log(Status.INFO, "Generated Request Body:<br><pre>" + requestBody + "</pre>");
            if (response != null) {
                test.log(Status.INFO, "Actual Response Code: " + response.getStatusCode());
                test.log(Status.INFO, "Actual Response Time: " + responseTime + "ms");
                test.log(Status.INFO, "Response Body:<br><pre>" + response.getBody().asPrettyString() + "</pre>");
            }

        } else {
            test.log(Status.PASS, "✅ Scenario passed successfully");

            if (endpoint != null)
                test.log(Status.INFO, "Endpoint: " + endpoint);
            if (requestBody != null)
            	test.log(Status.INFO, "Generated Request Body:<br><pre>" + requestBody + "</pre>");
            if (response != null) {
                test.log(Status.INFO, "Actual Response Code: " + response.getStatusCode());
                test.log(Status.INFO, "Actual Response Time: " + responseTime + "ms");
                test.log(Status.INFO, "Response Body:<br><pre>" + response.getBody().asPrettyString() + "</pre>");
            }
        }

        ExtentManager.closeReport(); 
    }
    
    @AfterAll 
    public static void sendEmailAfterTests() {
        EmailUtil.sendExtentReportByEmail();
    }
}
