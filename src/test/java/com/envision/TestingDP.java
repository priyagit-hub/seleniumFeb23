package com.envision;

import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import com.envision.automation.utils.FakeDataGenerator;
import org.testng.annotations.Test;

public class TestingDP {

    @DataProviderArgs(value="testCasePositive=username,password")
    @Test(dataProviderClass = DataProviderUtils.class,dataProvider="jsonDataProvider" )
    public  void testCasePositive(String username,String password) {
        System.out.println(username+"="+password);

    }

   //for Dynamic data
    @Test
    public  void testCasePositiveUsingFaker() {
        FakeDataGenerator fdG= new FakeDataGenerator();
        System.out.println(fdG.getUsername()+"="+fdG.getFirstName()+"="+fdG.getCellNumber());

    }

    // for both dataProvider and Faker
    /*@DataProviderArgs(value="10")
    @Test(dataProviderClass = DataProviderUtils.class,dataProvider="fakeLoginDataProvider" )
    public  void testCasePositiveUsingFaker(String username,String firstname ){
        FakeDataGenerator fdG= new FakeDataGenerator();
        System.out.println(fdG.getUsername()+"="+username+"="+firstname);

        //login script steps here
    }*/

    @DataProviderArgs(value="RegistrationTestData=firstname,lastName,address")
    @Test(dataProviderClass = DataProviderUtils.class,dataProvider="jsonDataProvider" )
    public  void RegistrationTestData(String firstname,String lastName ,String address ) {
        System.out.println(firstname+"="+lastName+"="+address);

    }


}
