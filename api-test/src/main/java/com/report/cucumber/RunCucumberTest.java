package com.report.cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty"},
        features = "src/test/resources/features",
        glue = {"com.report.cucumber.steps", "com.report.cucumber.hooks"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
