package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Features"}, 
glue = {"StepDefinitions","Hooks"},
plugin = {"pretty","html:reports/CucumberHTMLReport.html"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
