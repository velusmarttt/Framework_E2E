package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "stepDefinitions",
monochrome = true,tags = "@Regression", plugin = {"html:Cucumber_Report/cucumber.html"})
public class TestNGTest_Runner extends AbstractTestNGCucumberTests {

}
