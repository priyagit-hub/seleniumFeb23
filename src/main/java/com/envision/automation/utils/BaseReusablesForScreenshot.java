package com.envision.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseReusablesForScreenshot {
    //location where you want to save the screenshot
    static String screenshot="/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023/src/test/resources/screenshots/";
    public static void takeScreenshot(WebDriver driver)throws IOException {
        //convert the driver
        TakesScreenshot takeScreenshot= (TakesScreenshot) driver;
        //
        File image=takeScreenshot.getScreenshotAs(OutputType.FILE);
        // String fileName= screenshot+"Image1.png";// easy way to givename
       String fileName= screenshot+"Image-"+new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss").format(new Date())+".png";
        FileUtils.copyFile(image,new File(fileName));
    }
}
