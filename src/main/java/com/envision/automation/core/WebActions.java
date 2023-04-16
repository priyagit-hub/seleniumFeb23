package com.envision.automation.core;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WebActions implements IWebActions {
    public WebDriver driver;

    public WebActions(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public By getBy(String locatorType, String locatorValue) throws Exception {

        By by = null;
        if (locatorType.equalsIgnoreCase("id")) {
            by = By.id(locatorValue);
        } else if (locatorType.equalsIgnoreCase("name")) {
            by = By.name(locatorValue);
        } else if (locatorType.equalsIgnoreCase("class")) {
            by = By.className(locatorValue);
        } else if (locatorType.equalsIgnoreCase("lt")) {
            by = By.linkText(locatorValue);
        } else if (locatorType.equalsIgnoreCase("plt")) {
            by = By.partialLinkText(locatorValue);
        } else if (locatorType.equalsIgnoreCase("xpath")) {
            by = By.xpath(locatorValue);
        } else if (locatorType.equalsIgnoreCase("css")) {
            by = By.cssSelector(locatorValue);
        } else if (locatorType.equalsIgnoreCase("tag")) {
            by = By.tagName(locatorValue);
        } else {
            throw new Exception("Invalid locator type [" + locatorType + "] provided, please check the valid option");
        }

        return by;

    }


    @Override
    public WebElement findWebElement(String elementName) throws Exception {
        Properties properties = ObjectRepoReader.loadAllLocators();
        String propertyValue = properties.getProperty(elementName);
        String locatorType = propertyValue.split(";")[0];
        String locatorValue = propertyValue.split(";")[1];
        By by = getBy(locatorType, locatorValue);
        //WebElement element=driver.findElement(by);

        WebDriverWait wait = new WebDriverWait(driver, ConfigReader.longWaitTime);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        scrollToElement(element);
        return element;
    }

    @Override
    public List<WebElement> findAllWebElements(String elementName) throws Exception {
        Properties properties = ObjectRepoReader.loadAllLocators();
        String propertyValue = properties.getProperty(elementName);
        String locatorType = propertyValue.split(";")[0];
        String locatorValue = propertyValue.split(";")[1];
        By by = getBy(locatorType, locatorValue);
        //WebElement element=driver.findElement(by);

        WebDriverWait wait = new WebDriverWait(driver, ConfigReader.longWaitTime);
        List<WebElement> allElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        scrollToElement(allElements.get(0));
        return allElements;
    }

    @Override
    public void launchUrl(String url) {
        driver.get(url);
    }

    @Override
    public void clickOn(String elementName) throws Exception {
        WebElement element = findWebElement(elementName);
        new WebDriverWait(driver, ConfigReader.longWaitTime)
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();

    }

    @Override
    public void doubleClickOn(String elementName) throws Exception {
        Actions act = new Actions(driver);
        act.doubleClick(findWebElement(elementName))
                .build()
                .perform();
    }

    @Override
    public void rightClickOn(String elementName) throws Exception {
        Actions act = new Actions(driver);
        act.contextClick(findWebElement(elementName))
                .build()
                .perform();
    }

    @Override
    public void typeInto(String elementName, String text) throws Exception {
        WebElement element = findWebElement(elementName);
        new WebDriverWait(driver, ConfigReader.longWaitTime)
                .until(ExpectedConditions.elementToBeClickable(element)).click();
        Thread.sleep(200);
        element.clear();
        Thread.sleep(200);
        element.sendKeys(text);

    }

    @Override
    public String getTextFrom(String elementName) throws Exception {
        WebElement element = findWebElement(elementName);
        String text = element.getText();
        return text;
    }

    @Override
    public boolean isWebElementDisplayed(String elementName) throws Exception {
        WebElement element = findWebElement(elementName);
        return element.isDisplayed();
    }

    @Override
    public void takeScreenshot() throws IOException {
        if (ConfigReader.takeScreenshot) {
            TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
            File image = takeScreenshot.getScreenshotAs(OutputType.FILE);
            String fileName = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + "Image-" + new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss").format(new Date()) + ".png";
            FileUtils.copyFile(image, new File(fileName));
        }
    }

    @Override
    public void selectValueFromDropdown(String elementName, String how, String option) throws Exception {
        WebElement element = findWebElement(elementName);
        Select select = new Select(element);
        if (how.equalsIgnoreCase("value")) {
            select.selectByValue(option);
        } else if (how.equalsIgnoreCase("text")) {
            select.selectByVisibleText(option);
        } else if (how.equalsIgnoreCase("index")) {
            select.selectByIndex(Integer.parseInt(option));
        } else {
            throw new Exception("invalid 'mode' provided to select option");

        }
    }

    @Override
    public void switchToFrame(String elementName) throws Exception {
        driver.switchTo().frame(findWebElement(elementName));
    }

    @Override
    public void switchBackToMainPage() {
        driver.switchTo().defaultContent();
    }

    @Override
    public void switchToLastWindow() {
        Set<String> windows = driver.getWindowHandles();
        int length = windows.size();
        driver.switchTo().window(new ArrayList<String>(windows).get(length - 1));
    }

    @Override
    public void switchToSecondLastWindow() {
        Set<String> windows = driver.getWindowHandles();
        int length = windows.size();
        driver.switchTo().window(new ArrayList<String>(windows).get(length - 2));
    }

    @Override
    public void closeAllChildWindow(String windowId) {
        Set<String> windows = driver.getWindowHandles();
        int length = windows.size();
        List<String> sortedWindows = new ArrayList<String>(windows);
        for (int i = 0; i < sortedWindows.size(); i++) {
            if (!sortedWindows.get(i).equals(windowId)) {
                driver.switchTo().window(sortedWindows.get(i));
                driver.close();
            }
        }
        driver.switchTo().window(windowId);
    }

    @Override
    public void closeAllWindows() {
        Set<String> windows = driver.getWindowHandles();
        int length = windows.size();
        List<String> sortedWindows = new ArrayList<String>(windows);
        for (int i = 0; i < sortedWindows.size(); i++) {
            driver.switchTo().window(sortedWindows.get(i));
            driver.close();
        }
    }

    @Override
    public void dragAndDropTo(String elementDragFrom, String elementDragTo) throws Exception {
        Actions act = new Actions(driver);
        act.dragAndDrop(findWebElement(elementDragFrom), findWebElement(elementDragTo))
                .build()
                .perform();
    }

    @Override
    public void dragAndDropToUsingMouse(String elementDragFrom, String elementDragTo) throws Exception {
        Actions act = new Actions(driver);
        act.moveToElement(findWebElement(elementDragFrom)).clickAndHold(findWebElement(elementDragFrom))
                .moveToElement(findWebElement(elementDragTo))
                .release(findWebElement(elementDragFrom))
                .build()
                .perform();
    }

    @Override
    public void mouseHoverTo(String elementName) throws Exception {
        Actions act = new Actions(driver);
        act.moveToElement(findWebElement((elementName)))
                .build()
                .perform();
    }

    @Override
    public void scrollToElement(WebElement elementName) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", elementName);

    }

    @Override
    public void scrollToTopOfPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTO(0,-document.body.scrollHeight);");

    }
    @Override
    public void scrollToBottomOfPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTO(0,document.body.scrollHeight);");

    }

}