package stepDefinitions.employees;

import apiCommon.ApiEndpoints;
import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import model.Employee;
import model.responses.EmployeeResponse;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.put;

public class PutEmployees {
    ApiRequests apiRequests = new ApiRequests();

    @When("^Put request on v1/update with id : (.*)$")
    public void putRequestId(String employeeId) {
        Employee employee = (Employee) TestContext.INSTANCE.get("employeeReq");

        try {
            EmployeeResponse employeeResponse = apiRequests.putEmployee(employeeId, employee);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @When("^Put request on v1/update for the created resource$")
    public void putRequestManipulatedId() {
        String employeeId = "23";
        //endpoint only has mock data, employee is not created - using available data
        //String id = ((Map<String, Object>) response.jsonPath().get("data")).get("id");

        Employee employee = (Employee) TestContext.INSTANCE.get("employeeReq");

        try {
            EmployeeResponse employeeResponse = apiRequests.putEmployee(employeeId, employee);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @When("^Send invalid Put request on v1/update$")
    public void putRequestInvalidRequest() {
        String employeeId = "23";
        //endpoint only has mock data, employee is not created - using available data
        //String id = ((Map<String, Object>) response.jsonPath().get("data")).get("id");

         apiRequests.putEmployee(employeeId);
    }

}
