package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;
import utils.Logger;
import utils.TestContext;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class GetEmployee {

    @When("^Get request on v1/employee for id: (.*)$")
    public void getSingleEmployee(String id) {
        if (id.equals("[blank])")) {
            id = "";
        }
        Response response = get(ApiConstants.EMPLOYEE_ENDPOINT+id);
        Logger.log("response: "+ response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

    @When("^Get request on v1/employee for the user created above$")
    public void getCreatedEmployee() {
        //endpoint only has mock data, employee is not created - using available data
        //String id = ((Map<String, Object>) response.jsonPath().get("data")).get("id");
        String id = "23";

        Response response = get(ApiConstants.EMPLOYEE_ENDPOINT+id);
        Logger.log("response: "+ response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }
}
