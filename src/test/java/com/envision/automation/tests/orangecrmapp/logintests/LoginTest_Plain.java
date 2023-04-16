package com.envision.automation.tests.orangecrmapp.logintests;

import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import com.envision.automation.core.TestAssert;
import com.envision.automation.tests.orangecrmapp.BaseTest;
import org.testng.annotations.Test;

public class LoginTest_Plain extends BaseTest {
    @DataProviderArgs(value="testCasePositive=username,password,name")
    @Test(dataProvider = "jsonDataProvider",dataProviderClass= DataProviderUtils.class)
    public void validatePositiveLoginFlow(String username,String password,String name) throws Exception {
        actions.typeInto("LoginPage.tbxUsername",username);
        actions.typeInto("LoginPage.tbxPassword",password);
        actions.clickOn("LoginPage.btnSubmit");
        TestAssert.checkIfDisplayed(actions,"HomePage.lblDashboardHeading");

    }
}