package com.envision.automation.tests.orangecrmapp;

import com.envision.automation.core.BrowserFactory;
import com.envision.automation.core.ConfigReader;
import com.envision.automation.core.IWebActions;
import com.envision.automation.core.WebActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest {
    public WebDriver driver;
    public IWebActions actions;

    @BeforeSuite
    public void loadConfigurations() throws IOException {
        ConfigReader.loadFrameworkProperties();
        System.out.println("Before Suite");
    }

    @BeforeMethod
    public void initialiseBrowser() throws Exception{
        driver=BrowserFactory.openBrowser();
        actions=new WebActions(driver);
        System.out.println("Before Method");

    }

    @AfterMethod
    public void cleanUpTests()throws Exception{
        actions.takeScreenshot();
        driver.close();
    }
}
