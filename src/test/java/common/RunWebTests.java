package common;

import io.cucumber.testng.*;

@CucumberOptions(
        glue = "common.stepdefs",
        features = "src/test/resources/features/Banking/Web",
        plugin = {
                "pretty",
                "html:reports/tests/cucumber/cucumber-pretty.html",
                "testng:reports/tests/cucumber/testng/cucumber.xml",
                "json:reports/tests/cucumber/json/cucumberTestReport.json"
        }
)
public class RunWebTests extends AbstractTestNGCucumberTests {}
