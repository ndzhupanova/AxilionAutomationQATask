package PlanCalculatorPage;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanCalculatorPage extends BasePage {

    private static final String URL_PAGE = "https://axilion.z6.web.core.windows.net/#/";
    private static final By X_WAY_PULSE_BUTTON = By.xpath("//div[text()='X Way Pulse']");
    private static final By METRIC_BUTTON = By.xpath("//div[text()='METRIC']");
    private static final By IMPERIAL_BUTTON = By.xpath("//div[text()='IMPERIAL']");
    private static final double WEIGTH_LENGTH = 0.01;
    private static final double WEIGTH_JUNCTIONS = 0.02;
    private static final double XWU_REF = 5.625;
    static int roadLengthValue = 0;
    static int numberOfIntersectionsValue = 0;

    public static void goToUrl() {
        driver.get(URL_PAGE);
    }

    public static void clickOnXWayPulse() {
        BasePage.clickOnTheWebelement(X_WAY_PULSE_BUTTON);
        driver.findElement(By.xpath("//li[text()='X Way Pulse']")).click();

    }

    public static void clickOnXWayPulseTwin() {
        BasePage.clickOnTheWebelement(X_WAY_PULSE_BUTTON);
        driver.findElement(By.xpath("//li[text()='X Way (Pulse + Twin)']")).click();

    }

    public static void clickOnXWayPulseTwinNeural() {
        BasePage.clickOnTheWebelement(X_WAY_PULSE_BUTTON);
        driver.findElement(By.xpath("//li[text()='X Way (Pulse + Twin + Neural)']")).click();

    }

    public static void selectMetricUnit() {
        WebElement metricButton = driver.findElement(By.xpath("//div[text()='METRIC']"));

        if (!metricButton.isSelected()) {
            BasePage.clickOnTheWebelement(METRIC_BUTTON);
        }
    }

    public static void selectImperialUnit() {
        WebElement imperialButton = driver.findElement(By.xpath("//div[text()='IMPERIAL"));

        if (!imperialButton.isSelected()) {
            BasePage.clickOnTheWebelement(IMPERIAL_BUTTON);
        }
    }

    public static int setSliderValueRoadLength(int roadLength) {
        WebElement slider = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[1]/span/span[7]"));
        slider.click();
        slider.sendKeys(Keys.HOME);
        for (int i = 2; i <= roadLength; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        roadLengthValue = roadLength;
        return roadLength;
    }

    public static int setSliderValueNumberOfIntersections(int numberOfIntersections) {
        WebElement slider = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div[1]/div/div[2]/span/span[7]"));
        slider.click();
        slider.sendKeys(Keys.HOME);
        for (int i = 2; i <= numberOfIntersections; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        numberOfIntersectionsValue = numberOfIntersections;
        return numberOfIntersections;
    }

    public static double calculateTrafficComplexity() {
        int roadLength = roadLengthValue;
        int numberOfIntersections = numberOfIntersectionsValue;
        double trafficComplexity = (WEIGTH_LENGTH * roadLength) + (WEIGTH_JUNCTIONS * numberOfIntersections);
        return trafficComplexity;

    }

    public static int calculateXWU() {
        double trafficComplexity = PlanCalculatorPage.calculateTrafficComplexity();
        return (int) Math.ceil(XWU_REF * trafficComplexity);

    }


//    public static int extractUnitsIncluded() {
//        String suggestedPlanText = driver.findElement(SUGGESTET_PLAN).getText();
//        String[] parts = suggestedPlanText.split("\\+");
//        if (parts.length > 1) {
//            String lastPart = parts[parts.length - 1].trim();
//            String unitsString = lastPart.replaceAll("[^\\d]", "");
//            try {
//                return Integer.parseInt(unitsString);
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//                return 0;
//            }
//
//        }
//        return 0;
//    }

    public static int calculateSaaSPrice() {
        WebElement extendedName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[2]/div[2]"));
        String name = extendedName.getText();
        int flatRate = 0;
        int unitRate = 0;
        int unit = 0;

        if (name.matches(".*\\bX Way Pulse Starter\\b.*")) {
            flatRate = 980;
            unitRate = 70;
            unit = 14;
        } else if (name.matches(".*\\bX Way Pulse Standard\\b.*")) {
            flatRate = 1430;
            unitRate = 65;
            unit = 22;
        } else if (name.matches(".*\\bX Way Pulse Pro\\b.*")) {
            flatRate = 3000;
            unitRate = 60;
            unit = 50;
        } else if (name.matches(".*\\bX Way \\(Pulse \\+ Twin\\) Starter\\b.*")) {
            flatRate = 1260;
            unitRate = 90;
            unit = 14;
        } else if (name.matches(".*\\bX Way \\(Pulse \\+ Twin\\) Standard\\b.*")) {
            flatRate = 1760;
            unitRate = 80;
            unit = 22;
        } else if (name.matches(".*\\bX Way \\(Pulse \\+ Twin\\) Pro\\b.*")) {
            flatRate = 3750;
            unitRate = 75;
            unit = 50;
        } else if (name.matches(".*\\bX Way \\(Pulse \\+ Twin \\+ Neural\\) Starter\\b.*")) {
            flatRate = 1680;
            unitRate = 120;
            unit = 14;
        } else if (name.matches(".*\\bX Way \\(Pulse \\+ Twin \\+ Neural\\) Standard\\b.*")) {
            flatRate = 2530;
            unitRate = 115;
            unit = 22;
        } else if (name.matches(".*\\bX Way \\(Pulse \\+ Twin \\+ Neural\\) Pro\\b.*")) {
            flatRate = 5250;
            unitRate = 105;
            unit = 50;
        }

        int xwu = PlanCalculatorPage.calculateXWU();
        if (xwu <= unit) {
            return flatRate;
        } else {
            return flatRate + (xwu - unit) * unitRate;
        }
    }
    public static void print(){
        double trafficComplexity = PlanCalculatorPage.calculateTrafficComplexity();
        int XWU = PlanCalculatorPage.calculateXWU();
        System.out.println("Traffic Complexity = " + trafficComplexity);
        System.out.println("XWU = " + XWU);
        int price = (int) PlanCalculatorPage.calculateSaaSPrice();
        System.out.println("PRICE = " + price);
    }

}









