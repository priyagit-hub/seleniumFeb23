package com.envision.automation.core;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TestAssert {

    public static void CheckIfEquals(String expected,String actual){
        Assert.assertEquals(expected,actual,"values missmatch. Expected ["+expected+"],but actual "+actual );
    }

    public static void CheckIfNotEquals(String expected,String actual){
        Assert.assertNotEquals(expected,actual,"values matching , not different. Expected ["+expected+"],but actual "+actual );
    }

    public static void CheckIfTrue(boolean value){
        Assert.assertTrue(value,"values missmatch. Expected ["+value+"]" );
    }

    public static void CheckIfFalse(boolean value){
        Assert.assertFalse(value,"values missmatch. Expected false but found["+value+"]" );
    }
    public static void checkIfDisplayed(IWebActions actions,String elementName) throws Exception {
        WebElement element=actions.findWebElement(elementName);
        Assert.assertTrue(element.isDisplayed(), "Element is not displayed.");
    }

    public static void checkIfNotDisplayed(WebElement element) {
        Assert.assertFalse(element.isDisplayed(), "Element is  displayed.");
    }
}
