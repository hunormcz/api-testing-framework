package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.delete;

public class DeleteEmployee {

    @When("^Delete employee request for id: (.*)$")
    public void deleteSingleEmployee(String id) {
        if (id.equals("[blank])")) {
            id = "";
        }
        Logger.log("sending delete request for id: %s", id);
        Response response = delete( ApiConstants.DELETE_ENDPOINT+"2");
        Logger.log("response: "+ response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }
}
