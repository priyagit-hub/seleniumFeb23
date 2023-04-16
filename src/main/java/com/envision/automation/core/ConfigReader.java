package com.envision.automation.core;

import com.envision.automation.utils.PropertiesUtil;

import java.io.IOException;

public class ConfigReader {
    public static String environmentName;
    public static String browserName;
    public static Boolean takeScreenshot;
    public static String chromeBinaryPath;
    public static String firefoxBinaryPath;
    public static String edgeBinaryPath;
    public static int mediumWaitTime;
    public static int longWaitTime;
    public static int shortWaitTime;

    public static boolean headlessMode;

    public static boolean remoteRun;
            ;



    public static String appUrl;
    public static void loadFrameworkProperties () throws  IOException {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        propertiesUtil.loadAllProperties(System.getProperty("user.dir") + "/src/main/resources/config/config.properties");
        environmentName = propertiesUtil.getProperty ("environmentName");
        browserName = propertiesUtil.getProperty ("browserName");
        takeScreenshot = Boolean.parseBoolean(propertiesUtil.getProperty("takeScreenshot"));
        chromeBinaryPath = propertiesUtil.getProperty ("chromeBinaryPath");
        firefoxBinaryPath = propertiesUtil.getProperty("firefoxBinaryPath");
        edgeBinaryPath = propertiesUtil.getProperty("edgeBinaryPath");
        longWaitTime = Integer.parseInt(propertiesUtil.getProperty ("longWaitTime"));
        mediumWaitTime = Integer.parseInt(propertiesUtil.getProperty ("mediumWaitTime"));
        shortWaitTime = Integer.parseInt(propertiesUtil.getProperty("shortWaitTime"));
        appUrl= propertiesUtil.getProperty("appUrl");
        headlessMode= Boolean.parseBoolean(propertiesUtil.getProperty("headlessMode"));
        remoteRun=Boolean.parseBoolean(propertiesUtil.getProperty("remoteRun"));
    }

    public static void main(String[] args) throws IOException {
        ConfigReader.loadFrameworkProperties();
        System.out.println(environmentName);
        System.out.println(browserName);
    }

}
