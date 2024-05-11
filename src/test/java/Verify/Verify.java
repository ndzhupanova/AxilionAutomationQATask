package Verify;

import Base.BasePage;
import PlanCalculatorPage.PlanCalculatorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class Verify extends BasePage {
    private static final By HEADER = By.xpath("//*[@id=\"root\"]/div/div[1]/div");
    private static final By SUGGESTED_PLAN_TEXT = By.xpath("/html/body/div/div/div[2]/div[1]/div[2]/div[2]/span");
    private static final By EXTENDED_NAME = By.xpath("//div[@class='PlanPricing_suggestedPlan-planName__31ZTG']");
    private static final By SAAS_PRICE = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[3]/div[1]");
    private static final By SELECT_SERVICE_FIELD = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div/div");
    private static final By SUGGESTED_PLAN_FIELD = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]/span");
    static String title = driver.findElement(SUGGESTED_PLAN_TEXT).getText();

    public static void verifyHeaderTitle() {
        String header = driver.findElement(HEADER).getText();
        assertEquals("PLAN CALCULATOR", header, "Wrong Title!");

    }


    public static void verifyServicePlan(){
        String selectService=driver.findElement(SELECT_SERVICE_FIELD).getText();
        String suggestedPlan=driver.findElement(SUGGESTED_PLAN_FIELD).getText();
        assertEquals(selectService,suggestedPlan,"Difference between suggested and selected plan!");
    }

    public static void chekExtendedName() {
        int xwu = PlanCalculatorPage.calculateXWU();
        String extendedName = driver.findElement(EXTENDED_NAME).getText();
        if ((xwu >= 1 && xwu <= 28) && (!extendedName.matches(".*\\bStarter\\b.*"))) {
            System.out.println("Error: Title should contain 'Starter'");
        } else if ((xwu >= 29 && xwu <= 56) && (!extendedName.matches(".*\\bStandard\\b.*"))) {
            System.out.println("Error: Title should contain 'Standard'");
        } else if ((xwu >= 57 && xwu <= 1000) && (!extendedName.matches(".*\\bPro\\b.*"))) {
            System.out.println("Error: Title should contain 'Pro'");
        }
    }

    public static String extractSaaSPrice(String input) {

        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            result.append(matcher.group());
        }
        return result.toString().trim();

    }

    public static void verifySaaSPrice() {
        String saasPrice;
        String saas = extractSaaSPrice(saasPrice = driver.findElement(SAAS_PRICE).getText());
        assertEquals(Integer.parseInt(saas), PlanCalculatorPage.calculateSaaSPrice(), "Prices are not matching!");
    }

}

