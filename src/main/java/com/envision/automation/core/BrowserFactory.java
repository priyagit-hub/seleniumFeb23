package com.envision.automation.core;

import com.envision.automation.core.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;


    public class BrowserFactory {
        private static WebDriver driver;
        public static WebDriver openBrowser(){
            String browserName=ConfigReader.browserName;

            switch(browserName.toLowerCase()){
                case "chrome":
                    /*for headless
                    ChromeOptions chromeOptions= new ChromeOptions;
                    chromeOptions.setHeadless(ConfigReader.headlessMode);
                    System.setProperty(Constants.CHROME_DRIVER_PROPERTY,ConfigReader.chromeBinaryPath);
                    driver=new ChromeDriver(chromeOption);
                     */
                    System.setProperty(Constants.CHROME_DRIVER_PROPERTY,ConfigReader.chromeBinaryPath);
                    driver=new ChromeDriver();
                    break;
                case "edge":
                    System.setProperty(Constants.EDGE_DRIVER_PROPERTY,ConfigReader.chromeBinaryPath);
                    driver= new EdgeDriver();
                    break;
                default:
                    driver=null;
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(ConfigReader.mediumWaitTime, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(ConfigReader.longWaitTime, TimeUnit.SECONDS);
            return driver;
        }
    }

