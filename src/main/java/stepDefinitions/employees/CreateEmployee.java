package stepDefinitions.employees;

import apiCommon.ApiConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import utils.Logger;
import utils.TestContext;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class CreateEmployee {

    @Given("^Create employee request:")
    public void createRequest(Map<String, String> employeeData) {
        Logger.log("Creating request with employee data..");
        JSONObject request = new JSONObject();
        for (String key : employeeData.keySet()) {
            request.put(key, employeeData.get(key));
        }
        TestContext.INSTANCE.add("request", request);
    }

    @Given("^Create empty request:")
    public void createEmptyRequest() {
        Logger.log("Creating request with empty data..");
        JSONObject request = new JSONObject();
        TestContext.INSTANCE.add("request", request);

    }

    @When("^Send post request on /create$")
    public void sendPostRequest() {
        Logger.log("sending post request...");
        JSONObject request = (JSONObject) TestContext.INSTANCE.get("request");
        Logger.log("sending post request...");
        Response response = given()
                .contentType("application/json")
                .body(request.toJSONString())
                .when()
                .post(ApiConstants.CREATE_ENDPOINT);

        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

}
