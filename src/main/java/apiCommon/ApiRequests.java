package apiCommon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.Employee;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import org.json.simple.JSONObject;
import utils.ConfigManager;
import utils.Logger;
import utils.TestContext;

import static io.restassured.RestAssured.*;

public class ApiRequests {
    private String baseUrl = ConfigManager.getInstance().getBaseUrl();
    static ObjectMapper mapper = new ObjectMapper();

    public EmployeeResponse getEmployee(String id) throws JsonProcessingException {
        Logger.log("get request to employee with id %s", id);
        Response response = get(baseUrl + ApiEndpoints.EMPLOYEE_ENDPOINT + id);
        ;
        Logger.log("response: " + response.getBody().asString());

        TestContext.INSTANCE.add("response", response);

        return mapper.readValue(response.getBody().asString(), EmployeeResponse.class);
    }

    public EmployeesResponse getEmployees() throws JsonProcessingException {
        Logger.log("get request to all employees");
        Response response = get(baseUrl + ApiEndpoints.EMPLOYEES_ENDPOINT);
        Logger.log("response: ", response.getBody().asString());

        TestContext.INSTANCE.add("response", response);

        return mapper.readValue(response.getBody().asString(), EmployeesResponse.class);
    }

    public void postEmployee() {
        Logger.log("sending post request with partial data...");
        JSONObject request = (JSONObject) TestContext.INSTANCE.get("request");

        Response response = given()
                .contentType("application/json")
                .body(request)
                .when()
                .post(baseUrl + ApiEndpoints.CREATE_ENDPOINT);
        ;

        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

    }

    public EmployeeResponse postEmployee(Employee employee) throws JsonProcessingException {
        Logger.log("sending post request...");

        Response response = given()
                .contentType("application/json")
                .body(employee)
                .when()
                .post(baseUrl + ApiEndpoints.CREATE_ENDPOINT);
        ;

        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

        return mapper.readValue(response.getBody().asString(), EmployeeResponse.class);
    }

    public EmployeeResponse putEmployee(String id, Employee employee) throws JsonProcessingException {

        Logger.log("sending put request for id: %s", id);
        Response response = given()
                .contentType("application/json")
                .body(employee)
                .when().put(baseUrl + ApiEndpoints.UPDATE_ENDPOINT + id);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

        return mapper.readValue(response.getBody().asString(), EmployeeResponse.class);
    }

    public void putEmployee(String id) {
        Logger.log("sending put request with partial data...");
        JSONObject request = (JSONObject) TestContext.INSTANCE.get("request");

        Logger.log("sending put request for id: %s", id);
        Response response = given()
                .contentType("application/json")
                .body(request)
                .when().put(baseUrl + ApiEndpoints.UPDATE_ENDPOINT + id);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

    public void deleteEmployee(String id) {
        Logger.log("sending delete request for id: %s", id);
        Response response = delete(baseUrl + ApiEndpoints.DELETE_ENDPOINT + "2");
        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }
}
