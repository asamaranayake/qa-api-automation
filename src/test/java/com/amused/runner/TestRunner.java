package com.amused.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@CucumberOptions(
        plugin = {
                "pretty","json:target/cucumber-report/cucumber-output.json",
                "html:target/cucumber-report/cucumber-output.html",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
        },
        features = {"src/test/resources/features/"},
        glue = {"com.amused.testSteps"},
        tags = "@api")


public class TestRunner extends AbstractTestNGCucumberTests {

    public static ThreadLocal<String> tests = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    @BeforeTest
    public void tearUp(ITestContext iTestContext) {
        tests.set(iTestContext.getName());
        logger.info(">>>>>>>>> Started-"+tests.get());
    }

    @AfterTest
    public void tearDown() {
        logger.info(">>>>>>>>> End-"+tests.get());

    }

}
