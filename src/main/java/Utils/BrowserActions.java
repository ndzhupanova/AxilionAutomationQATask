package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserActions {

    private static WebDriver driver;

    public static void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static void tearDown(){
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}
