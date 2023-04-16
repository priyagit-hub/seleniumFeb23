package com.envision.automation.core;

import com.envision.automation.utils.PropertiesUtil;

import java.io.IOException;
import java.util.Properties;

public class ObjectRepoReader {

    public static Properties loadAllLocators () throws IOException {
        PropertiesUtil propertiesUtil = new PropertiesUtil();
        Properties properties=propertiesUtil.loadAllProperties(System.getProperty("user.dir") + "/src/main/resources/objectrepository/OR.properties");
       // System.out.println(properties.getProperty("Homepage.lblDashboardHeading"));
        return properties;
    }

    public static void main(String[] args) throws IOException {
        loadAllLocators();
    }
}
