package com.envision.automation.core;

import com.envision.automation.core.DataProviderArgs;
import com.envision.automation.core.DataProviderUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstselenuimprogWithTestNG extends BeforeAfterAnnotations{
    @DataProviderArgs(value="testCasePositive=username,password")
   @Test(dataProviderClass = DataProviderUtils.class,dataProvider="jsonDataProvider" )
    public  void testCasePositive(String username,String password) {
       WebElement tbxUsername= driver.findElement(By.name("username"));
       tbxUsername.sendKeys(username);
       WebElement  tbxPassword= driver.findElement(By.xpath("//input[@type='password' and @name='password']"));
        tbxPassword.sendKeys(password);
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
       // WebElement element=driver.findElement(By.xpath("//p[text()='My Actions']"));
        WebElement element=driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        //Assert.assertTrue(element.isDisplayed(),"My Actions");
       Assert.assertTrue(element.isDisplayed(),"Dashboard");


    }
    @Test
    public void testcaseNegative(){

        driver.findElement(By.name("username")).sendKeys("Admi");
        driver.findElement(By.name("password")).sendKeys("admin1");
        driver.findElement(By.xpath("//button[text()=' Login ']")).click();
        WebElement elementLoginText=driver.findElement(By.xpath("//label[text()='Username']"));
        Assert.assertTrue(elementLoginText.isDisplayed(),"Testcase failed");
        WebElement element=driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
        Assert.assertEquals(element.getText(),"Invalid credentials");
    }

}


