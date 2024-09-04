package org.sharfulumair.cucumberOptions;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src//test//java//org//sharfulumair//features",
        plugin = "json:target/jsonReports/cucumber-report.json",
        glue = "org.sharfulumair.stepDefinations"
)
public class TestRunner {


}
