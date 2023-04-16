package com.envision.application.orangecrm.pages;

import com.envision.automation.core.ConfigReader;
import com.envision.automation.core.WebActions;
import org.openqa.selenium.WebDriver;

    public class LoginPage extends WebActions {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public LoginPage navigateToWebsite(){
        driver.get(ConfigReader.appUrl);
        return this;
    }
    public LoginPage enterUsername(String username) throws Exception {
    typeInto("LoginPage.tbxUsername",username);
    return this;
    }

    public LoginPage enterPassword(String password) throws Exception {
      typeInto("LoginPage.tbxPassword",password);
      return this;
    }

    public HomePage hitSubmit() throws Exception {
        clickOn("LoginPage.btnSubmit");
        return new HomePage(driver);
    }
}
