package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/GetEmployeesTests.json"},
        glue = "",
        features = {
                "src/main/resources/features/getEmployees.feature",

        })
public class GetEmployeesTests {
}
