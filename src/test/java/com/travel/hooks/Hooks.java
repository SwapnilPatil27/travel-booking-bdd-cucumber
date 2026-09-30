package com.travel.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.travel.driver.DriverFactory;
import io.cucumber.java.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class Hooks {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @BeforeAll
    public static void beforeAll() {
        ExtentSparkReporter reporter =
                new ExtentSparkReporter("target/ExtentReports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Before
    public void setUp(Scenario scenario) {
        DriverFactory.initDriver();
        TEST.set(extent.createTest(scenario.getName()));
        TEST.get().info("Browser started");
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                File screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.FILE);
                String safeName = scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_");
                File destination = new File("target/screenshots/" + safeName + ".png");
                destination.getParentFile().mkdirs();
                FileUtils.copyFile(screenshot, destination);
                scenario.attach(FileUtils.readFileToByteArray(destination),
                        "image/png", "Failure Screenshot");
                TEST.get().fail("Scenario failed");
            } else {
                TEST.get().pass("Scenario passed");
            }
        } catch (Exception e) {
            TEST.get().warning("Unable to capture failure screenshot: " + e.getMessage());
        } finally {
            DriverFactory.quitDriver();
            TEST.remove();
        }
    }

    @AfterAll
    public static void afterAll() {
        if (extent != null) {
            extent.flush();
        }
    }
}
