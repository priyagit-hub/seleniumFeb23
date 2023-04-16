import com.envision.automation.core.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Test
public class Datepicker {
    WebDriver driver = BrowserFactory.openBrowser();
    String Url = "https://demoqa.com/forms";

    public void datePicker() throws IOException {
        driver.findElement(By.id("dateOfBirthInput")).click();
        LocalDateTime ldt = LocalDateTime.now().plusMonths(9).plusYears(3).plusDays(12);// its generating future date .
        String month = ldt.getMonth().toString();
        month = month.substring(0, 1) + month.substring(1).toLowerCase();
        String year = String.valueOf(ldt.getYear());
        String day = String.valueOf(ldt.getDayOfMonth());
        //find the Element and select month and year By visibleText ()
        WebElement monthElement = driver.findElement(By.className("react-datepicker__month-select"));
        Select monthDropdown = new Select(monthElement);
        monthDropdown.selectByVisibleText(month);
        WebElement yearElement = driver.findElement(By.className("react-datepicker__year-select"));
        Select yearDropdown = new Select(yearElement);
        yearDropdown.selectByVisibleText(year);
        //Selecting date
        List<WebElement> dayOfMonthElements = driver.findElements(By.xpath("//div[contains(@class,'react-datepicker__day--')and not(contains(@class,'outside-month'))]"));
        for (WebElement element : dayOfMonthElements) {
            if (element.getText().equals(day)) {
                element.click();
                break;
            }
        }
    }
}