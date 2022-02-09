package apiCommon;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Employee;
import model.EmployeeServiceHelper;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import utils.TestContext;

import static net.serenitybdd.rest.SerenityRest.given;


public class ApiRequests {
    private static String baseUrl = TestContext.INSTANCE.getBaseUrl();
    private static Logger log = LogManager.getLogger(ApiRequests.class);


    public EmployeeResponse getEmployee(String id) throws JsonProcessingException {
        log.info("get request to employee with id %s", id);

        String url = baseUrl + ApiEndpoints.EMPLOYEE_ENDPOINT + id;
        Response response = getRequest(url);

        log.info("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
        return EmployeeServiceHelper.converToEmployee(response.getBody().asString());
    }

    public EmployeesResponse getEmployees() throws JsonProcessingException {
        log.info("get request to all employees");
        String url = baseUrl + ApiEndpoints.EMPLOYEES_ENDPOINT;
        Response response = getRequest(url);

        log.info("response: ", response.getBody().asString());

        TestContext.INSTANCE.add("response", response);

        return EmployeeServiceHelper.converToEmployees(response.getBody().asString());
    }

    public void createEmployee() {
        log.info("sending post request with partial data...");
        JSONObject body = (JSONObject) TestContext.INSTANCE.get("request");

        String url = baseUrl + ApiEndpoints.CREATE_ENDPOINT;
        Response response = postRequest(url, body);

        log.info("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }


    public EmployeeResponse createEmployee(Employee employee) throws JsonProcessingException {
        log.info("sending post request...");

        String url = baseUrl + ApiEndpoints.CREATE_ENDPOINT;
        Response response = postRequest(url, employee);

        log.info("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

        return EmployeeServiceHelper.converToEmployee(response.getBody().asString());
    }

    public EmployeeResponse putEmployee(String id, Employee employee) throws JsonProcessingException {

        log.info("sending put request for id: %s", id);

        String url = baseUrl + ApiEndpoints.UPDATE_ENDPOINT+id;
        Response response = putRequest(url, employee);
        log.info("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

        return EmployeeServiceHelper.converToEmployee(response.getBody().asString());
    }

    public void putEmployee(String id) {
        log.info("sending put request with partial data...");
        JSONObject body = (JSONObject) TestContext.INSTANCE.get("request");

        log.info("sending put request for id: %s", id);
        String url = baseUrl + ApiEndpoints.UPDATE_ENDPOINT;
        Response response = putRequest(url, body);

        log.info("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

    public void deleteEmployee(String id) {
        log.info("sending delete request for id: %s", id);

        String url = baseUrl + ApiEndpoints.DELETE_ENDPOINT + id;
        Response response = deleteRequest(url);

        log.info("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }

    private Response getRequest(String url) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(url);
    }

    private Response postRequest(String url, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url);
    }

    private Response deleteRequest(String url) {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(url);
    }

    private Response putRequest(String url, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().put(url);
    }
}
