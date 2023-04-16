package com.envision.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    Properties properties;
    //step 1: read the file and load all properties
    public Properties loadAllProperties (String filePath) throws IOException {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File(filePath)));
        }catch(Exception e){
            System.out.println("unable to load properties file, \n something wrong, \n pleaase check the path:"+ filePath+"");
        }
        return properties;
    }
    //step 2: fetch all properties;
    public String getProperty(String propertyName){
        String propertyValue;
      try {
          propertyValue = properties.getProperty(propertyName);
      }
      catch(Exception e){
          System.out.println("Missing property ["+propertyName+"] or something wrong please check properties file");
          throw e;
      }
        return propertyValue;
    }
    public static void main (String[] args) throws IOException {
        PropertiesUtil obj = new PropertiesUtil();
        obj.loadAllProperties("/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023/src/main/resources/config/config.properties");
        System.out.println(obj.getProperty("environmentName"));

    }
}
