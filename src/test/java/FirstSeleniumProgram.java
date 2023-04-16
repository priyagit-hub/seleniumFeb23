import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumProgram {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/ishanudey/IdeaProjects/SeleniumProject_Feb2023/src/test/binaries/chromedriver");
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(3000);
        driver.navigate().to("https://www.Google.com");
        driver.close();



    }

}
