package stepDefinitions.employees;

import apiCommon.ApiConstants;
import apiCommon.ApiRequests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.DataTableType;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import utils.Logger;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.TestContext;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GetEmployees {

    @When("^Get request on v1/employees$")
    public void getRequest() {
        try {
            EmployeesResponse employeesResponse = ApiRequests.getEmployees();
            TestContext.INSTANCE.add("employeesResponse", employeesResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}
