package apiCommon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import org.json.simple.JSONObject;
import utils.ConfigFileReader;
import utils.Logger;
import utils.TestContext;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class ApiRequests {
    private static String baseUrl = ConfigFileReader.getInstance().getBaseUrl();
    static ObjectMapper mapper = new ObjectMapper();

    public static EmployeeResponse getEmployee(String id) throws JsonProcessingException {
        Logger.log("get request to employee with id %s", id);
        Response response = get(baseUrl + ApiConstants.EMPLOYEE_ENDPOINT + id);;
        Logger.log("response: " + response.getBody().asString());

        TestContext.INSTANCE.add("response", response);

        return mapper.readValue(response.getBody().asString(), EmployeeResponse.class);
    }

    public static EmployeesResponse getEmployees() throws JsonProcessingException {
        Logger.log("get request to all employees");
        Response response = get(baseUrl + ApiConstants.EMPLOYEES_ENDPOINT);
        Logger.log("response: ", response.getBody().asString());

        TestContext.INSTANCE.add("response", response);

        return mapper.readValue(response.getBody().asString(), EmployeesResponse.class);
    }

    public static void createEmployeeRequest(Map<String, String> employeeData) {
        Logger.log("Creating request with employee data..");
        JSONObject request = new JSONObject();
        for (String key : employeeData.keySet()) {
            request.put(key, employeeData.get(key));
        }
        TestContext.INSTANCE.add("request", request);
    }

    public static void createEmployeeEmptyRequest() {
        Logger.log("Creating request with employee data..");
        JSONObject request = new JSONObject();
        TestContext.INSTANCE.add("request", request);
    }



    public static void createEmployee() {
        Logger.log("sending post request...");
        JSONObject request = (JSONObject) TestContext.INSTANCE.get("request");

        Logger.log("sending post request...");
        Response response = given()
                .contentType("application/json")
                .body(request.toJSONString())
                .when()
                .post(ApiConstants.CREATE_ENDPOINT);;

        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

//        return mapper.readValue(response.getBody().asString(), EmployeeResponse.class);
    }

    public static void updateEmployee(String id) {
        Logger.log("sending put request...");
        JSONObject request = (JSONObject) TestContext.INSTANCE.get("request");

        Logger.log("sending put request for id: %s", id);
        Response response = put(ApiConstants.UPDATE_ENDPOINT +id);
        Logger.log("response: ", response.getBody().asString());
        TestContext.INSTANCE.add("response", response);

//        return mapper.readValue(response.getBody().asString(), EmployeeResponse.class);
    }

    public static void deleteEmployee(String id)  {
        Logger.log("sending delete request for id: %s", id);
        Response response = delete(ApiConstants.DELETE_ENDPOINT + "2");
        Logger.log("response: " + response.getBody().asString());
        TestContext.INSTANCE.add("response", response);
    }


}
