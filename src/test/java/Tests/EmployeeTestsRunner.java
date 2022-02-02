package Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/EmployeeTests.json"},
        glue = "stepDefinitions",
        features = {
                "src/main/resources/features/createEmployee.feature",
                "src/main/resources/features/getEmployee.feature",
                "src/main/resources/features/getEmployees.feature",
                "src/main/resources/features/putEmployee.feature",
                "src/main/resources/features/deleteEmployee.feature"

        })
public class EmployeeTestsRunner {
}
