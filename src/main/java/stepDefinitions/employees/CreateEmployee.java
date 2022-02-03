package stepDefinitions.employees;

import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import model.Employee;
import model.EmployeeServiceHelper;
import model.responses.EmployeeResponse;
import utils.TestContext;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class CreateEmployee {
    ApiRequests apiRequests = new ApiRequests();

    @Given("^Create employee request:")
    public void createRequest(Map<String, String> employeeData) {
        Employee employee = EmployeeServiceHelper.employeeMapper(employeeData);
        TestContext.INSTANCE.add("employeeReq", employee);
    }

    @Given("^Create employee request with missing data:")
    public void createPartialRequest(Map<String, String> employeeData) {
        EmployeeServiceHelper.createEmployeeRequest(employeeData);
    }

    @Given("^Create empty request:")
    public void createEmptyRequest() {
        EmployeeServiceHelper.createEmployeeEmptyRequest();
    }

    @When("^Send post request on /create$")
    public void sendPostRequest() {
        Employee employee = (Employee) TestContext.INSTANCE.get("employeeReq");

        try {
            EmployeeResponse employeeResponse = apiRequests.postEmployee(employee);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @When("^Send invalid post request on /create$")
    public void sendInvalidPostRequest() {
        apiRequests.postEmployee();
    }


}
