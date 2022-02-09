package stepDefinitions.employees;

import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.When;
import model.responses.EmployeeResponse;
import utils.TestContext;

public class PutEmployees {
    ApiRequests apiRequests = new ApiRequests();

    @When("^Put request on /update with id : (.*)$")
    public void putRequestId(String employeeId) {

        try {
            EmployeeResponse employeeResponse = apiRequests.putEmployee(employeeId);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @When("^Put request on /update for the created resource$")
    public void putRequestManipulatedId() {
        String employeeId = "23";
        //endpoint only has mock data, employee is not created - using available data
        //String id = ((Map<String, Object>) response.jsonPath().get("data")).get("id");

        try {
            EmployeeResponse employeeResponse = apiRequests.putEmployee(employeeId);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
