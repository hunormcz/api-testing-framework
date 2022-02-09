package apiCommon;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.EmployeeServiceHelper;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.TestContext;

import javax.ws.rs.HttpMethod;

import static net.serenitybdd.rest.SerenityRest.given;


public class ApiRequests {

    private static Logger log = LogManager.getLogger(ApiRequests.class);


    public EmployeeResponse getEmployee(String id) throws JsonProcessingException {
        log.info("Get employee with id: &s", id);

        Response response = callEndpointWithMethod(
                ApiHelper.generateEndpointUrl(ApiEndpoints.EMPLOYEE_ENDPOINT, id)
                , HttpMethod.GET);
        return EmployeeServiceHelper.convertToEmployee(response.getBody().asString());
    }

    public EmployeesResponse getEmployees() throws JsonProcessingException {
        log.info("Get all employees..");

        Response response = callEndpointWithMethod(
                ApiHelper.generateEndpointUrl(ApiEndpoints.EMPLOYEES_ENDPOINT)
                , HttpMethod.GET);
        return EmployeeServiceHelper.convertToEmployees(response.getBody().asString());
    }

    public EmployeeResponse createEmployee() throws JsonProcessingException {
        log.info("sending post request...");

        Response response = callEndpointWithMethod(
                ApiHelper.generateEndpointUrl(ApiEndpoints.CREATE_ENDPOINT)
                , HttpMethod.POST);
        return EmployeeServiceHelper.convertToEmployee(response.getBody().asString());
    }

    public EmployeeResponse putEmployee(String id) throws JsonProcessingException {
        log.info("sending put request for id: %s", id);

        Response response = callEndpointWithMethod(
                ApiHelper.generateEndpointUrl(ApiEndpoints.UPDATE_ENDPOINT + id)
                , HttpMethod.PUT);
        return EmployeeServiceHelper.convertToEmployee(response.getBody().asString());
    }

    public void deleteEmployee(String id) {
        log.info("sending delete request for id: %s", id);

        callEndpointWithMethod(
                ApiHelper.generateEndpointUrl(ApiEndpoints.DELETE_ENDPOINT, id)
                , HttpMethod.DELETE);
    }

    public Response callEndpointWithMethod(String url, String method) {
        Response response;
        Object body = TestContext.INSTANCE.get("requestBody");
        switch (method) {
            case HttpMethod.GET:
                response = getRequest(url);
                break;
            case HttpMethod.POST:
                response = postRequest(url, body);
                break;
            case HttpMethod.PUT:
                response = putRequest(url, body);
                break;
            case HttpMethod.DELETE:
                response = deleteRequest(url);
                break;
            default:
                throw new Error("Method not supported: " + method);
        }
        log.info("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
        return response;
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
