package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;

    // Initializes the report
    public static ExtentReports getReporter() {
        if (extent == null) {
            // Set the location for the report to be saved
            sparkReporter = new ExtentSparkReporter("target/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    // Close the report
    public static void closeReport() {
        extent.flush();
    }
}
