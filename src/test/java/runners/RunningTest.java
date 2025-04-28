package runners;

import org.junit.platform.suite.api.IncludeEngines;

import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectFile;
import org.junit.platform.suite.api.SelectMethod;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.ConfigurationParameter;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/PostRequest.feature")
@SelectClasspathResource("features/GetRequest.feature")
@SelectClasspathResource("features/PatchRequest.feature")
@SelectClasspathResource("features/DeleteRequest.feature")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "stepDefinitions,hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports.html, json:target/cucumber-reports.json")
@ConfigurationParameter(key = "cucumber.publish", value = "true")
public class RunningTest {
}
