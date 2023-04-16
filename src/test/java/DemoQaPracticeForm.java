import com.envision.automation.core.BrowserFactory;
import com.envision.automation.utils.BaseReusablesForScreenshot;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DemoQaPracticeForm  {
    WebDriver driver= BrowserFactory.openBrowser();
    String Url="https://demoqa.com/forms";
    @Test
    public void tableValidation() throws InterruptedException, AWTException, IOException {
        driver.navigate().to(Url);
        //fetching data from properties
        Properties property= new Properties();
        FileInputStream inputStream= new FileInputStream("/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023/src/test/resources/properties/testdata.properties");
        property.load(inputStream);
        //click on Practice form
        driver.findElement(By.xpath("//span[normalize-space()='Practice Form']")).click();
        WebElement firstNameElement=driver.findElement(By.id("firstName"));
        firstNameElement.sendKeys(property.getProperty("FirstName"));
        //input Lastname
        WebElement lastNameElement=driver.findElement(By.id("lastName"));
        lastNameElement.sendKeys(property.getProperty("LastName"));
        //input Emailid
        WebElement emailElement=driver.findElement(By.xpath("//input[@id='userEmail']"));
        emailElement.sendKeys(property.getProperty("Email"));
        //select gender
        FluentWait <WebDriver>fw= new WebDriverWait(driver,120).pollingEvery(Duration.ofSeconds(2));
        fw.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[normalize-space()='Female']"))).click();
        //select 10 digit mobileNo
        driver.findElement(By.xpath("//input[@id='userNumber']")).sendKeys(property.getProperty("Mobile"));
        //input DateOfBirth
        driver.findElement(By.id("dateOfBirthInput")).click();
        WebElement monthElement=driver.findElement(By.className("react-datepicker__month-select"));
        Select monthDropdown=new Select(monthElement);
        monthDropdown.selectByVisibleText(property.getProperty("Month"));
        WebElement yearElement=driver.findElement(By.className("react-datepicker__year-select"));
        Select yearDropdown=new Select(yearElement);
        yearDropdown.selectByVisibleText(property.getProperty("Year"));
        //Selecting date
        List<WebElement> dayOfMonthElements=driver.findElements(By.xpath("//div[contains(@class,'react-datepicker__day--')and not(contains(@class,'outside-month'))]"));
        for(WebElement element:dayOfMonthElements){
            if(element.getText().equals(property.getProperty("DayOfMonth"))){
                element.click();
                break;
            }
        }
        //Select subject
        WebElement subjectElement=driver.findElement(By.id("subjectsInput"));
        subjectElement.sendKeys(property.getProperty("Subjects"));
        subjectElement.sendKeys(Keys.ENTER);
        //select hobby music
        driver.findElement(By.xpath("//label[normalize-space()='Music']")).click();
        //upload picture
        driver.findElement(By.id("uploadPicture")).sendKeys("/users/ishanudey/Documents/priyanka/flower.jpeg");
        //Current address
        driver.findElement(By.id("currentAddress")).sendKeys(property.getProperty("Currentaddress"));
        //select State and city
        WebElement stateElement=driver.findElement(By.id("react-select-3-input"));
        stateElement.sendKeys(property.getProperty("State"));
        stateElement.sendKeys(Keys.ENTER);
        WebElement cityElement=driver.findElement(By.id("react-select-4-input"));
        cityElement.sendKeys(property.getProperty("City"));
        cityElement.sendKeys(Keys.ENTER);
        WebElement submitElement=driver.findElement(By.id("submit"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",submitElement);
        List<WebElement>allRows=driver.findElements(By.tagName("tr"));
        //System.out.println("Size"+allRows.size());
        Map<String,String> rowData=new HashMap<String,String>();
        for(int i=1;i<allRows.size();i++){
            List<WebElement>allColumns=allRows.get(i).findElements(By.tagName("td"));
            rowData.put(allColumns.get(0).getText(),allColumns.get(1).getText());
            System.out.println(allColumns.get(0).getText());
            System.out.println(allColumns.get(1).getText());
        }
        Assert.assertEquals(rowData.get("Student Name"),property.getProperty("FirstName")+" "+ property.getProperty("LastName"));
        Assert.assertEquals(rowData.get("Student Email"),property.getProperty("Email"));
        Assert.assertEquals(rowData.get("Gender"),"Female");
        Assert.assertEquals(rowData.get("Mobile"),property.getProperty("Mobile"));
        Assert.assertEquals(rowData.get("Subjects"),property.getProperty("Subjects"));
        Assert.assertEquals(rowData.get("Hobbies"),"Music");
        Assert.assertEquals(rowData.get("Address"),property.getProperty("Currentaddress"));
        Assert.assertEquals(rowData.get("State and City"),property.getProperty("State")+" "+property.getProperty("City"));
        BaseReusablesForScreenshot.takeScreenshot(driver);
        driver.quit();

    }
}


