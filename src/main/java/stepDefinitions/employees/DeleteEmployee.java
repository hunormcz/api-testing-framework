package stepDefinitions.employees;

import apiCommon.ApiConstants;
import apiCommon.ApiRequests;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.delete;

public class DeleteEmployee {

    @When("^Delete employee request for id: (.*)$")
    public void deleteSingleEmployee(String id) {
        ApiRequests.deleteEmployee(id);
    }
}
