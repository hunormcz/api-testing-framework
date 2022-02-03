package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/EmployeeTests.json"},
        glue = "stepDefinitions",
        features = {
                "src/main/resources/features/getEmployee.ee.feature"

        })
public class GetEmployeeTests {
}
