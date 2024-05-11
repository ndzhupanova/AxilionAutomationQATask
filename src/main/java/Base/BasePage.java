package Base;

import Utils.BrowserActions;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

    protected static WebDriver driver;

    static {driver= BrowserActions.getDriver();}


    public static void clickOnTheWebelement(By locator){
        driver.findElement(locator).click();
    }

}
