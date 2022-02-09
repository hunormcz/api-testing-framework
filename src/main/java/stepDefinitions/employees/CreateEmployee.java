package stepDefinitions.employees;

import apiCommon.ApiHelper;
import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import model.Employee;
import model.EmployeeServiceHelper;
import model.responses.EmployeeResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.TestContext;

import java.util.Map;


public class CreateEmployee {
    ApiRequests apiRequests = new ApiRequests();
    private static Logger log = LogManager.getLogger(ApiRequests.class);

    @Given("^Create employee request data:")
    public void createRequest(Map<String, String> employeeData) {
        Employee employee = EmployeeServiceHelper.employeeMapper(employeeData);
        TestContext.INSTANCE.add("requestBody", employee);
    }

    @Given("^Create employee request data with missing parameters:")
    public void createPartialRequest(Map<String, String> employeeData) {
        ApiHelper.createEmployeeRequestData(employeeData);
    }

    @Given("^Create empty request data:")
    public void createEmptyRequest() {
        ApiHelper.createEmployeeEmptyRequestData();
    }

    @When("^Send post request on /create$")
    public void sendPostRequest() {
        try {
            EmployeeResponse employeeResponse = apiRequests.createEmployee();
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            log.error(e.getStackTrace());
        }
    }

}
