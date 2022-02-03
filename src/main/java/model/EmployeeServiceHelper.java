package model;

import org.json.simple.JSONObject;
import utils.Logger;
import utils.TestContext;

import java.security.cert.CertificateRevokedException;
import java.util.Map;

public class EmployeeServiceHelper {
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
        if (expected.getAge()!=actual.getAge()){
            differences.append(String.format("Expected emplyee age: %s, Actual: %s", expected.getAge(), actual.getAge()));
        }
        if (!expected.getName().equals(actual.getName())){
            differences.append(String.format("Expected emplyee name: %s, Actual: %s", expected.getName(), actual.getName()));
        }
        if (!expected.getSalary().toString().equals(actual.getSalary().toString())){
            differences.append(String.format("Expected emplyee salary: %s, Actual: %s", expected.getSalary(), actual.getSalary()));
        }

        return differences.toString();
    }

    public static void createEmployeeEmptyRequest() {
        Logger.log("Creating request with employee data..");
        JSONObject request = new JSONObject();
        TestContext.INSTANCE.add("request", request);
    }

    public static void createEmployeeRequest(Map<String, String> employeeData) {
        Logger.log("Creating request with employee data..");
        JSONObject request = new JSONObject();
        for (String key : employeeData.keySet()) {
            request.put(key, employeeData.get(key));
        }
        TestContext.INSTANCE.add("request", request);
    }

}

