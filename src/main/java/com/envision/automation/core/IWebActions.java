package com.envision.automation.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public interface IWebActions {
    public abstract WebElement findWebElement (String elementName) throws Exception;
    public List<WebElement> findAllWebElements(String elementName) throws Exception;

    void launchUrl(String url);

    public abstract void clickOn (String element) throws Exception;


    void doubleClickOn(String elementname) throws Exception;

    void rightClickOn(String elementName) throws Exception;

    public void typeInto(String elementName, String text) throws Exception;

    public abstract String getTextFrom(String elementName) throws Exception;


    public abstract By getBy(String locatorType,String locatorValue) throws Exception;


    boolean isWebElementDisplayed(String elementName) throws Exception;

    void takeScreenshot()throws IOException;

    void selectValueFromDropdown(String elementName, String how, String option) throws Exception;

    void switchToFrame(String elementName) throws Exception;

    void switchBackToMainPage();

    void switchToLastWindow();

    void switchToSecondLastWindow();

    void closeAllChildWindow(String windowId);

    void closeAllWindows();

    void dragAndDropTo(String elementDragFrom, String elementDragTo) throws Exception;

    void dragAndDropToUsingMouse(String elementDragFrom, String elementDragTo) throws Exception;

    void mouseHoverTo(String elementName) throws Exception;

    void scrollToElement(WebElement elementName);

    void scrollToTopOfPage();

    void scrollToBottomOfPage();
}
