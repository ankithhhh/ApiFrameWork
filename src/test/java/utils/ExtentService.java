package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentService {

    private static ExtentTest test;

    // Start a new test with the provided name
    public static void startTest(String testName) {
        test = ExtentManager.getReporter().createTest(testName);
    }

    // Log a step to the report
    public static void log(Status status, String message) {
        if (test != null) {
            test.log(status, message);
        }
    }

    // End the current test
    public static void endTest() {
        if (test != null) {
            ExtentManager.getReporter().flush();
        }
    }
}
