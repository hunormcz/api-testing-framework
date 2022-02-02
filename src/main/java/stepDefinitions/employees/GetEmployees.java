package stepDefinitions.employees;

import apiCommon.ApiConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.restassured.http.Header;
import model.responses.Employees;
import utils.Logger;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.TestContext;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GetEmployees {

    @When("^Get request on v1/employees$")
    public void getRequest() {
        Response response = get(ApiConstants.EMPLOYEES_ENDPOINT);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }



    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}
