package com.envision.automation.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BeforeAfterAnnotations {
    WebDriver driver;
    String url="https://opensource-demo.orangehrmlive.com";
 @BeforeSuite
  public void initializeFrameworkConfigurations() throws IOException {
     ConfigReader.loadFrameworkProperties();
 }
 @BeforeMethod
 public void settingBrowserProperties(){
    System.setProperty("webdriver.chrome.driver",ConfigReader.chromeBinaryPath);
    driver =new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(ConfigReader.mediumWaitTime, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(ConfigReader.longWaitTime,TimeUnit.SECONDS);
    driver.navigate().to(url);
}

@AfterMethod
public void closingBrowser(){
    driver.close();
}


}
