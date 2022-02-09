package stepDefinitions.employees;

import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.When;
import model.responses.EmployeeResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.TestContext;


public class GetEmployee {
    ApiRequests apiRequests = new ApiRequests();
    private static Logger log = LogManager.getLogger(ApiRequests.class);
    @When("^Get request on /employee for id: (.*)$")
    public void getSingleEmployee(String id) {
        try {
            EmployeeResponse employeeResponse = apiRequests.getEmployee(id);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    @When("^Get request on /employee with empty Id$")
    public void getEmployeeEmptyId() {

        try {
            EmployeeResponse employeeResponse = apiRequests.getEmployee("");
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {

        }
    }


    @When("^Get request on /employee for the user created above$")
    public void getCreatedEmployee() {
        //endpoint only has mock data, employee is not created - using available data
//        Response response = (Response) TestContext.INSTANCE.get("response");
//        String id = ((Map<String, Object>) response.jsonPath().get("data")).get("id");
        String id = "23";

        try {
            EmployeeResponse employeeResponse = apiRequests.getEmployee(id);
            TestContext.INSTANCE.add("employeeResponse", employeeResponse);
        } catch (JsonProcessingException e) {
            log.error(e.getStackTrace());
        }
    }
}
