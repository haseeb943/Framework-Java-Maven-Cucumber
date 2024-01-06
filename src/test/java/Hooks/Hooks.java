package Hooks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import PageObjectModel.loginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static WebDriver driver;
    private static loginPage login;
    private static ExtentReports extent;
    private static ExtentTest logger;
    private static List<String> scenarioSteps = new ArrayList<>();

    String projectPath = System.getProperty("user.dir");

    @Before
    public void beforeTestMethod(Scenario scenario) {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                    projectPath + File.separator + "reports" + File.separator + "extentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.DARK);
            extent.setSystemInfo("UserName", "HASEEB");
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Automation Test Report");
        }

        //logger = extent.createTest(scenario.getName());
        logger = extent.createTest(scenario.getName())
                .assignCategory(scenario.getName());
        
        driver = new ChromeDriver();
        login = new loginPage(driver);

        logger.log(Status.INFO, MarkupHelper.createLabel("Scenario: " + scenario.getName(), ExtentColor.BLUE));
        scenarioSteps.clear();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
    	
        if (!scenarioSteps.isEmpty()) {
            for (String step : scenarioSteps) {
                if (scenario.getStatus() == io.cucumber.java.Status.PASSED) {
                    logger.log(Status.PASS, MarkupHelper.createLabel("Step: " + step, ExtentColor.GREEN));
                } else if (scenario.getStatus() == io.cucumber.java.Status.FAILED) {
                    logger.log(Status.FAIL, MarkupHelper.createLabel("Step: " + step, ExtentColor.RED));
                }
            }
            scenarioSteps.clear();
        }
    }

    @After
    public void afterMethod(Scenario scenario) {
        if (scenario.isFailed()) {
        	
            logger.log(Status.FAIL,
                    MarkupHelper.createLabel(scenario.getName() + " Scenario Failed", ExtentColor.RED));
            String screenshotPath = captureScreenshot(scenario.getName());
            try {
                logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath(screenshotPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

        	logger.log(Status.PASS,
                    MarkupHelper.createLabel(scenario.getName() + " Scenario Passed", ExtentColor.GREEN));
        }

        if (driver != null) {
            driver.quit();
        }

        extent.flush();
    }

    private String captureScreenshot(String testName) {
        String screenshotPath = projectPath + File.separator + "screenshots" + File.separator + testName + ".png";
        File f1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f1, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static loginPage getLoginPOM() {
        return login;
    }

    public static void addStep(String step) {
        scenarioSteps.add(step);
    }
}
