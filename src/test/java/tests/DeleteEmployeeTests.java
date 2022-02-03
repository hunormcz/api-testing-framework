package tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/jsonReports/DeleteEmployeeTests.json"},
        glue = "",
        features = {
                "src/main/resources/features/deleteEmployee.feature"

        })
public class DeleteEmployeeTests {
}
