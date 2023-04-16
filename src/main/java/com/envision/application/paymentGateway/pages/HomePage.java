package com.envision.application.paymentGateway.pages;

import com.envision.application.orangecrm.pages.LoginPage;
import com.envision.automation.core.ConfigReader;
import com.envision.automation.core.WebActions;
import org.openqa.selenium.WebDriver;
    public class HomePage extends WebActions {

        public HomePage(WebDriver driver){
            super(driver);
        }
        public HomePage navigateToWebsite(){
            driver.get(ConfigReader.appUrl);
            return this;
        }

        public void clickonGenerateCardNumber(){


        }

}
