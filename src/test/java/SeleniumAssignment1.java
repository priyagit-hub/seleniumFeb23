import com.envision.automation.utils.BaseReusablesForScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SeleniumAssignment1 {
    String url="https://opensource-demo.orangehrmlive.com";
    public  void testCasePositive() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver","/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023/src/test/binaries/chromedriver");
       WebDriver driver =new ChromeDriver();
       driver.manage().window().maximize();
       driver.navigate().to( "https://opensource-demo.orangehrmlive.com");
       driver.manage().timeouts().pageLoadTimeout(20000,TimeUnit.SECONDS);
       driver.manage().timeouts().implicitlyWait(20000,TimeUnit.SECONDS);
       driver.findElement(By.name("username")).sendKeys("Admin");
       driver.findElement(By.name("password")).sendKeys("admin123");
       driver.findElement(By.xpath("//button[text()=' Login ']")).click();
       WebElement element=driver.findElement(By.xpath("//p[text()='My Actions']"));
       if (element.isDisplayed()){
           System.out.println("Testcase successful ");
       }
       else{
           System.out.println("testcase failed");
       }
      Thread.sleep(10000);
        BaseReusablesForScreenshot.takeScreenshot(driver);
       driver.close();

    }
    public static void main(String[] args) throws InterruptedException, IOException{
        SeleniumAssignment1 s_obj=new SeleniumAssignment1();
        s_obj.testCasePositive();

    }

}
