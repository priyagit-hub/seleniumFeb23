package com.envision.automation.core;

import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.utils.FakeDataGenerator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DataProviderUtils {
    @DataProvider(name = "loginDataProvider")
    public static Object[][] provideMultipleDataSet() { //simple way
        Object[][] obj = new Object[][]{{"Admin", "admin123"}, {"Admin123", "admin123"}};
        return obj;
    }

    @DataProvider(name = "fakeLoginDataProvider")
    public static Object[][] fakeLoginDataProvider(Method method) { //using both dataprovider+faker
        String count = method.getAnnotation(DataProviderArgs.class).value();
        int counter=Integer.parseInt(count);
        Object[][] obj=new Object[counter][2];
        for(int i=0;i<obj.length;i++){
            obj[i][0]=new FakeDataGenerator().getUsername();
            obj[i][1]=new FakeDataGenerator().getFirstName();
        }
        return obj;
    }


    @DataProvider(name = "jsonDataProvider")
    public static Object[][] fetchTestCaseData(Method method) throws IOException, ParseException {
        String MethodName = method.getName(); //will give you the method which it running
        String testDataName = method.getAnnotation(DataProviderArgs.class).value().split("=")[0];// testCasePositive
        String testDataFields = method.getAnnotation(DataProviderArgs.class).value().split("=")[1];//username,password
        // File jsonFile= new File("/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023"+"src/test/resources/testData/testData.json");
        //provide relative path
        String loginDataFields = testDataFields;//for selecting specific data field
        String loginDataArr[] = loginDataFields.split(",");
        //provide relative path
       // File jsonFile = new File(System.getProperty("user.dir") + File.separator + "src/test/resources/testData/testData.json");
        File jsonFile = new File(System.getProperty("user.dir") + File.separator + "src/test/resources/testData/testData-"+System.getProperty("envName")+".json");

        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(jsonFile);
        Object obj = parser.parse(fr);
        System.out.println("obj"+obj);
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println("jsob obj"+jsonObject);
        JSONArray jsonArray = (JSONArray) jsonObject.get(testDataName);
        List<List<String>> allRows = new ArrayList<List<String>>();

        for (int i = 0; i < jsonArray.size(); i++) {
            List<String> allColumnsInRow = new ArrayList<String>();
            JSONObject testCaseObj = (JSONObject) jsonArray.get(i);
            for (int j = 0; j < loginDataArr.length; j++) {
                System.out.println(testCaseObj.get(loginDataArr[j]));
                allColumnsInRow.add(testCaseObj.get(loginDataArr[j]).toString());
            }
            allRows.add(allColumnsInRow);
        }
        System.out.println(allRows);
        Object[][] dataObj = new Object[allRows.size()][allRows.get(0).size()];
        int index = 0;
        for (List<String> list : allRows) {
            for (int i = 0; i < list.size(); i++) {
                dataObj[index][i] = list.get(i);
            }
            index++;
        }
        return dataObj;
    }

    public static void main(String[] args) throws IOException, ParseException {
        String loginDataFields = "username,password";//for selecting specific data field
        String loginDataArr[] = loginDataFields.split(",");
        // File jsonFile= new File("/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023"+"src/test/resources/testData/testData.json");
        //provide relative path
        File jsonFile = new File(System.getProperty("user.dir") + File.separator + "src/test/resources/testData/testData.json");
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(jsonFile);
        Object obj = parser.parse(fr);
        System.out.println("Inside main"+obj);
        JSONObject jsonObject = (JSONObject) obj;
        System.out.println("Inside main jsonObject"+jsonObject);
        //for reading all the field all data
       /* JSONArray jsonArray= (JSONArray)jsonObject.get("testCasePositive");
        for (int i=0;i<jsonArray.size();i++){
            JSONObject testCaseObj=(JSONObject)jsonArray.get(i);
            System.out.println(testCaseObj);
        }
        */
        //for reading specific field data
       /*
        JSONArray jsonArray = (JSONArray) jsonObject.get("testCasePositive");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject testCaseObj = (JSONObject) jsonArray.get(i);
            for (int j = 0; j < loginDataArr.length; j++) {
                System.out.println(testCaseObj.get(loginDataArr[j]));
            }
        }
        */
        // for making the Data in Object[][] format ....that i can return object
        JSONArray jsonArray = (JSONArray) jsonObject.get("testCasePositive");
        List<List<String>> allRows = new ArrayList<List<String>>();

        for (int i = 0; i < jsonArray.size(); i++) {
            List<String> allColumnsInRow = new ArrayList<String>();
            JSONObject testCaseObj = (JSONObject) jsonArray.get(i);
            for (int j = 0; j < loginDataArr.length; j++) {
                System.out.println(testCaseObj.get(loginDataArr[j]));
                allColumnsInRow.add(testCaseObj.get(loginDataArr[j]).toString());
            }
            allRows.add(allColumnsInRow);
        }
        System.out.println(allRows);
        Object[][] dataObj = new Object[allRows.size()][allRows.get(0).size()];
        int index = 0;
        for (List<String> list : allRows) {
            for (int i = 0; i < list.size(); i++) {
                dataObj[index][i] = list.get(i);
            }
            index++;
        }
    }

}