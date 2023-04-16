package com.envision.automation.tests.orangecrmapp.logintests;

import com.envision.application.orangecrm.pages.HomePage;
import com.envision.application.orangecrm.pages.LoginPage;
import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import com.envision.automation.core.TestAssert;
import com.envision.automation.tests.orangecrmapp.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @DataProviderArgs(value="testCasePositive=username,password,name")
    @Test(dataProvider = "jsonDataProvider",dataProviderClass= DataProviderUtils.class)
    public void validatePositiveLoginFlow(String username,String password,String name) throws Exception {
        //Plain page object model


      /*  LoginPage loginPage =new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        HomePage homePage=loginPage.hitSubmit();
         TestAssert.CheckIfTrue(homePage.isDashboardHeadingDisplayed());
            */

        //Fluent object model
        System.out.println("Inside validatePositiveLoginFlow");
        LoginPage loginPage =new LoginPage(driver);
        HomePage homePage= loginPage.navigateToWebsite()
                .enterUsername(username)
                    .enterPassword(password)
                        .hitSubmit();
        TestAssert.CheckIfTrue(homePage.isDashboardHeadingDisplayed());




    }
}