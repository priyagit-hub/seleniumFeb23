package com.envision;
import com.envision.automation.core.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PaymentGatewayAssignment5 {
    WebDriver driver= BrowserFactory.openBrowser();
    String Url="https://demo.guru99.com/payment-gateway/index.php";
    @Test
    public void paymentGateway() throws InterruptedException {
        driver.navigate().to(Url);
        driver.findElement(By.xpath("//a[normalize-space()='Generate Card Number']")).click();
        //windowHandel
        Set<String> window=driver.getWindowHandles();
        Iterator<String> iterator=window.iterator();
        String window1=iterator.next();
        String window2= iterator.next();
        driver.switchTo().window(window2);
        //scroll down
        JavascriptExecutor scroll=  (JavascriptExecutor)driver;
        scroll.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        //fetching Data
        WebElement element1= driver.findElement(By.xpath("//h4[contains(text(),'Card Number')]"));
        String cardNumber=element1.getText().substring(13);
        WebElement element2=driver.findElement(By.xpath("//h4[contains(text(),'CVV')]"));
        String cvv= element2.getText().substring(6);
        WebElement element3=driver.findElement(By.xpath("//h4[contains(text(),'Exp')]"));
        String expDate= element3.getText().substring(5);
        String expMonth=expDate.substring(1,3);
        System.out.println("month"+ expMonth);
        String expYear=expDate.substring(4);
        System.out.println( "year "+ expYear);
        WebElement element4=driver.findElement(By.xpath("//h4[contains(text(),'Credit Limit')]"));
        String creditLimit= element4.getText().substring(14);
        System.out.println("CardNumber is "+cardNumber);
        System.out.println("CVV is "+ cvv);
        System.out.println("Expiry date is "+ expDate);
        System.out.println("Credit Limit is $ "+creditLimit);
        // back to previous driver
        driver.switchTo().window(window1);
        driver.findElement(By.xpath("//nav[@id='nav']/a"));
        //chage quantity
        WebElement QuantityElement=driver.findElement(By.xpath("//select[@name='quantity']"));
        Select se= new Select(QuantityElement);
        se.selectByIndex(3);
        //price of toy
        WebElement priceElement=driver.findElement(By.xpath("//h3[contains(text(),'Price')]"));
        String price= priceElement.getText().substring(8);
        System.out.println("Price of the Elephant Soft Toy $"+price);
        //click on buy now
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        //Enter card details
        driver.findElement(By.id("card_nmuber")).sendKeys(cardNumber);
        WebElement expdateElement=driver.findElement(By.xpath("//select[@name='month']"));
        Select sel= new Select(expdateElement);
        sel.selectByVisibleText(expMonth);
        WebElement expYearElement=driver.findElement(By.xpath("//select[@id='year']"));
        Select selyear= new Select(expYearElement);
        selyear.selectByVisibleText(expYear);
        driver.findElement(By.id("cvv_code")).sendKeys(cvv);
        //assert visible pay text is 20*4
        WebElement payElement=driver.findElement(By.xpath("//input[@name='submit']"));
        Float price1=Float.parseFloat(price)*4;
        Float price2=Float.parseFloat(payElement.getAttribute("value").substring(5));
        Assert.assertEquals(price1,price2);
        //verify payment successful message is there
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        WebElement textElement=driver.findElement(By.xpath("//h2[text()='Payment successfull!']"));
        Assert.assertEquals(textElement.getText(),"Payment successfull!");
        //fetch orderID
        List<WebElement> allRows=driver.findElements(By.xpath("//table[@class='alt access' ]"));
        List<WebElement> allColumns = allRows.get(0).findElements(By.tagName("td"));
        // System.out.println(allColumns.get(0).getText());for column 1
        String orderId=allColumns.get(1).getText();
        System.out.println("OrderId is:"+orderId);
        //click on check credit card limit
        driver.findElement(By.xpath("//nav[@id='nav']/a[text()='Check Credit Card Limit']")).click();
        // click on popup
        WebElement frame1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
        if (frame1.isDisplayed()) {
            driver.switchTo().frame(frame1);
            WebElement btnAdClose = driver.findElement(By.xpath("//div[@id='dismiss-button']/div"));
            System.out.println("I am inside");
            btnAdClose.click();
            driver.switchTo().defaultContent();
        }
        //enter credit card number
        driver.findElement(By.xpath("//input[@id='card_nmuber']")).sendKeys(cardNumber);
        driver.findElement(By.xpath("//input[@name='submit']")).click();
        //verify card balance,
        float cardBalance=Float.parseFloat(creditLimit)-price1;
        System.out.println("cardBalance"+cardBalance);
        WebElement cardBalanceElement=driver.findElement(By.xpath("//h4[contains(text(),'Credit Card Balance')]"));
        String cardBalance1=cardBalanceElement.getText().substring(20);
        System.out.println("balance left"+cardBalance1);
        Assert.assertEquals(Float.parseFloat(cardBalance1),cardBalance);
        //finding orderID again
        List<WebElement> allRows1=driver.findElements(By.xpath("//table[@class='alt']"));
        List<WebElement> allColumns1 = allRows1.get(0).findElements(By.tagName("td"));
        String orderId1= allColumns1.get(5).getText();
        Assert.assertEquals(orderId1,orderId);
        Thread.sleep(2000);
        driver.quit();
    }

}


