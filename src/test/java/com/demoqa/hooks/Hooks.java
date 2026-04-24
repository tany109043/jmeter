package com.demoqa.hooks;

import com.demoqa.base.DriverFactory;
import com.demoqa.utils.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import io.qameta.allure.Allure;

public class Hooks {

    @Before
    public void setup() {

        // Initialize browser
        DriverFactory.initDriver();

        // Get URL from config
        String url = ConfigReader.get("url");

        // 🔍 Debug print (VERY IMPORTANT)
        System.out.println("URL from config: " + url);

        // Open URL
        DriverFactory.getDriver().get(url);
    }

    @After
    public void tearDown(Scenario scenario) {

        // Take screenshot if failed
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment("Failure Screenshot",
                    new ByteArrayInputStream(screenshot));
        }

        // Close browser
        DriverFactory.quitDriver();
    }
}