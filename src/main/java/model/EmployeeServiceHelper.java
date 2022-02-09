package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.responses.EmployeeResponse;
import model.responses.EmployeesResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestContext;

import java.util.Map;

public class EmployeeServiceHelper {
    private static Logger log = LoggerFactory.getLogger(EmployeeServiceHelper.class);
    
    public static Employee employeeMapper(Map<String, String> employeeData) {
        Employee employee = null;
        try {
            employee = new Employee(employeeData.get("name"),Integer.parseInt(employeeData.get("salary")),  Integer.parseInt(employeeData.get("age")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static String compareEmployees(Employee expected, Employee actual) {
        StringBuilder differences = new StringBuilder();
        if (!expected.getAge().equals(actual.getAge())){
            differences.append(String.format("Expected employee age: %s, Actual: %s", expected.getAge(), actual.getAge()));
        }
        if (!expected.getName().equals(actual.getName())){
            differences.append(String.format("Expected emplyee name: %s, Actual: %s", expected.getName(), actual.getName()));
        }
        if (!expected.getSalary().equals(actual.getSalary())){
            differences.append(String.format("Expected emplyee salary: %s, Actual: %s", expected.getSalary(), actual.getSalary()));
        }

        return differences.toString();
    }

    public static void createEmployeeEmptyRequestData() {
        log.info("Creating request with employee data..");
        JSONObject request = new JSONObject();
        TestContext.INSTANCE.add("request", request);
    }

    public static void createEmployeeRequest(Map<String, String> employeeData) {
        log.info("Creating request with employee data..");
        JSONObject request = new JSONObject();
        for (String key : employeeData.keySet()) {
            request.put(key, employeeData.get(key));
        }
        TestContext.INSTANCE.add("request", request);
    }

    public static EmployeeResponse converToEmployee(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, EmployeeResponse.class);
    }
    public static EmployeesResponse converToEmployees(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, EmployeesResponse.class);
    }

}

