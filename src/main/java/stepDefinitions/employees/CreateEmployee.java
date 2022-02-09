package stepDefinitions.employees;

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
        TestContext.INSTANCE.add("employeeReq", employee);
    }

    @Given("^Create employee request data with missing parameters:")
    public void createPartialRequest(Map<String, String> employeeData) {
        EmployeeServiceHelper.createEmployeeRequest(employeeData);
    }

    @Given("^Create empty request data:")
    public void createEmptyRequest() {
        EmployeeServiceHelper.createEmployeeEmptyRequestData();
    }

    @When("^Send post request on /create$")
    public void sendPostRequest() {
        Employee employee = (Employee) TestContext.INSTANCE.get("employeeReq");

        try {
            EmployeeResponse employeeResponse = apiRequests.createEmployee(employee);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            log.error(e.getStackTrace());
        }
    }

    @When("^Send invalid post request on /create$")
    public void sendInvalidPostRequest() {
        apiRequests.createEmployee();
    }


}
