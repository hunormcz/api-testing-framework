package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/PostEmployeeTests.json"},
        glue = "stepDefinitions",
        features = {
                "src/main/resources/features/createEmployee.feature",

        })
public class PostEmployeeTests {
}
