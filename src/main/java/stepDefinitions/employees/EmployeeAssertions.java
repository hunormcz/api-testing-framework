package stepDefinitions.employees;

import io.cucumber.java.en.And;
import model.Employee;
import model.EmployeeServiceHelper;
import model.responses.EmployeeResponse;
import utils.TestContext;

import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class EmployeeAssertions {

    @And("^The employee is returned with data:")
    public void assertEmployeeData(Map<String, String> expectedData) {
        EmployeeResponse employee = (EmployeeResponse) TestContext.INSTANCE.get("employeeResponse");
        String differences = comparator(expectedData, employee.getEmployee());
        assertThat("Returned values are NOT correct:" + differences, differences.equals(""), is(true));
    }

    @And("^The employee is returned with correct data")
    public void assertEmployeeData() {
        EmployeeResponse actualEmployee = (EmployeeResponse) TestContext.INSTANCE.get("employeeResponse");
        Employee expectedEmployee = (Employee) TestContext.INSTANCE.get("requestBody");

        String differences = EmployeeServiceHelper.compareEmployees(expectedEmployee, actualEmployee.getEmployee());
        assertThat("Returned values are NOT correct:" + differences, differences.equals(""), is(true));
    }


    @And("^Employee response has status (.*) and message (.*)")
    public void assertEmployeeData(String expectedStatus, String expectedMessage) {
        EmployeeResponse employee = (EmployeeResponse) TestContext.INSTANCE.get("employeeResponse");

        assertThat(String.format("Expected employee resposnse status: %s, actual: %s",expectedStatus,employee.getStatus() )
                ,expectedStatus.equals(employee.getStatus())
                ,is(true));
        assertThat(String.format("Expected employee resposnse message: %s, actual: %s",expectedMessage,employee.getEmployee() )
                ,expectedMessage.equals(employee.getMessage())
                ,is(true));
    }

    private String comparator(Map<String, String> expected, Employee actual) {
        StringBuilder errormessage = new StringBuilder();
        if (!actual.getName().equals(expected.get("name"))) {
            errormessage.append(String.format("Expected name: %s, Actual: %s", expected.get("name"), actual.getName()));
        }
        if (!actual.getAge().toString().equals(expected.get("age"))) {
            errormessage.append(String.format("Expected age: %s, Actual: %s", expected.get("age"), actual.getAge()));
        }
        if (!actual.getSalary().toString().equals(expected.get("salary"))) {
            errormessage.append(String.format("Expected salary: %s, Actual: %s", expected.get("salary"), actual.getSalary()));
        }
        return errormessage.toString();
    }
}
