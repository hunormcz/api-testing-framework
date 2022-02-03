package stepDefinitions.employees;

import apiCommon.ApiRequests;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.delete;

public class DeleteEmployee {
    ApiRequests apiRequests = new ApiRequests();

    @When("^Delete employee request for id: (.*)$")
    public void deleteSingleEmployee(String id) {
        apiRequests.deleteEmployee(id);
    }
}
