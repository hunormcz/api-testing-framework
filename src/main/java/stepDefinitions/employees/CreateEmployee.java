package stepDefinitions.employees;

import apiCommon.ApiConstants;
import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.responses.EmployeeResponse;
import org.json.simple.JSONObject;
import utils.Logger;
import utils.TestContext;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class CreateEmployee {

    @Given("^Create employee request:")
    public void createRequest(Map<String, String> employeeData) {
        ApiRequests.createEmployeeRequest(employeeData);
    }

    @Given("^Create empty request:")
    public void createEmptyRequest() {
        ApiRequests.createEmployeeEmptyRequest();
    }

    @When("^Send post request on /create$")
    public void sendPostRequest() {
            ApiRequests.createEmployee();
    }

}
