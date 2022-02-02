package stepDefinitions.employees;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import io.restassured.response.Response;
import model.Employee;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import utils.TestContext;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ResponseAssertions {

    @And("^The response has the following data:")
    public void assertPartialData(Map<String, String> expectedData) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        Map<String, Object> actualData = response.jsonPath()
                .get("data");

        Boolean identical = expectedData.entrySet().stream().allMatch(e -> e.getValue().equals(String.valueOf(actualData.get(e.getKey()))));
        assertThat("Returned values are correct", identical, is(true));
    }

    @And("^The response returns message: (.*)")
    public void assertResponseMessag(String message) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        String actualMessage = response.jsonPath()
                .get("message");

        assertThat("Returned response does not contain" + message, actualMessage.equals(message), is(true));
    }

    @Then("^Response code is (.*)$")
    public void assertResponseCode(Integer code) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        assertThat(String.format("Invalid response code! Expected: %s, Actual: %s", code, response.getStatusCode()), response.getStatusCode() == code, is(true));
    }

    @Then("^Response status is (.*)$")
    public void assertResponseStatus(String status) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        assertThat(String.format("Invalid response code! Expected: %s, Actual: %s", status, response.getStatusLine()), response.getStatusLine().contains(status), is(true));
    }

    @Then("^Response schema corresponds with baseline: (.*)$")
    public void assertResponseSchema(String baseline) {
        Response response = (Response) TestContext.INSTANCE.get("response");

        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath(baseline));
    }

    @Then("^Response body contains expected number of employees: (.*)")
    public void assertResponseBody(int number) throws JsonProcessingException {
        Response response = (Response) TestContext.INSTANCE.get("response");
        String body = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        try {
            EmployeesResponse employees = mapper.readValue(body, EmployeesResponse.class);

            assertThat("Number of employees is returned is different from expected", employees.getEmployees().size() == number, is(true));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Then("^Response headers are returned:")
    public void assertResponseHeaders(Map<String, String> headers) {
        Response response = (Response) TestContext.INSTANCE.get("response");
        StringBuilder errormessage = new StringBuilder();

        Map<String, String> actualheaders = response.getHeaders().asList().stream().sorted(Comparator.comparing(Header::getName)).collect(Collectors.toMap(Header::getName, Header::getValue, (v1, v2) -> v2));
//        Boolean identical = headers.entrySet().stream().allMatch(e -> e.getValue().equals(actualheaders.get(e.getKey())));

        assertThat(String.format("Expected headers size: %s, Actual: %s", headers.size(), actualheaders.entrySet().size()), headers.size() == actualheaders.entrySet().size(), is(true));
        for (String key : headers.keySet()) {

            if (!headers.get(key).equals(actualheaders.get(key)) && (!("ExpiresDate").contains(key))) {
                errormessage.append(String.format("invalid header value, Expected: %s, Actual: %s", headers.get(key), actualheaders.get(key)));
            }

        }
        assertThat(String.format("Failed: %s", errormessage.toString()), errormessage.toString().equals(""), is(true));
    }
}
