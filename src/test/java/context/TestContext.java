package context;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {

    private RequestSpecification request;
    private Response response;
    private String requestBody;
    private String endpoint;
    private long responseTime;

    public TestContext() {
        // default constructor needed for Cucumber to inject context
    }

    // Getters and Setters
    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }
}
