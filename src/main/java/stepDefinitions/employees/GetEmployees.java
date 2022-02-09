package stepDefinitions.employees;

import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;
import model.responses.EmployeesResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.TestContext;

public class GetEmployees {
    ApiRequests apiRequests = new ApiRequests();
    private static Logger log = LogManager.getLogger(ApiRequests.class);
    @When("^Get request on /employees$")
    public void getRequest() {
        try {
            EmployeesResponse employeesResponse = apiRequests.getEmployees();
            TestContext.INSTANCE.add("employeesResponse", employeesResponse);
        } catch (JsonProcessingException e) {
            log.error(e.getStackTrace());
        }
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}
