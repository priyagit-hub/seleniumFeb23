package com.envision.application.orangecrm.pages;

import com.envision.automation.core.WebActions;
import org.openqa.selenium.WebDriver;

public class HomePage extends WebActions {

    public HomePage(WebDriver driver){
        super(driver);
    }
    public boolean isDashboardHeadingDisplayed() throws Exception {
     return   isWebElementDisplayed("HomePage.lblDashboardHeading");

    }

}
