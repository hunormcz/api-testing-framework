package apiCommon;

import model.EmployeeServiceHelper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestContext;

import java.util.Map;

public class ApiHelper {
    private static Logger log = LoggerFactory.getLogger(EmployeeServiceHelper.class);
    private static String baseUrl = TestContext.INSTANCE.getBaseUrl();

    public static String generateEndpointUrl(String endpoint){
        return baseUrl+endpoint;
    }
    public static String generateEndpointUrl(String endpoint, String id){
        return baseUrl+endpoint+id;
    }

    public static void createEmployeeEmptyRequestData() {
        log.info("Creating request with employee data..");
        JSONObject request = new JSONObject();
        TestContext.INSTANCE.add("requestBody", request);
    }

    public static void createEmployeeRequestData(Map<String, String> employeeData) {
        log.info("Creating request with employee data..");
        JSONObject request = new JSONObject();
        for (String key : employeeData.keySet()) {
            request.put(key, employeeData.get(key));
        }
        TestContext.INSTANCE.add("requestBody", request);
    }
}
