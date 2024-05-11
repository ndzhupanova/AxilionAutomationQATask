package PlanCalculatorTest;

import Base.BaseTest;
import PlanCalculatorPage.PlanCalculatorPage;
import Utils.WaitTool;
import Verify.Verify;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlanCalculatorTest extends BaseTest {
    @Test
    public static void XWayPulseService() {
        PlanCalculatorPage.goToUrl();  // Navigate to Plan Calculator Page
        Verify.verifyHeaderTitle();   //Assert that the expected title matches the actual
        PlanCalculatorPage.clickOnXWayPulse();  //Select X Way Pulse Service from the dropdown
        Verify.verifyServicePlan();  //Assert that the suggested plan matches the selected one
        PlanCalculatorPage.selectMetricUnit(); //Select Metric Unit
        int roadLength = PlanCalculatorPage.setSliderValueRoadLength(10); //Set value to Road Length Slider
        int numberOfIntersections = PlanCalculatorPage.setSliderValueNumberOfIntersections(10);  //Set value to Number of intersections slider
        Verify.chekExtendedName(); // Assert that the correct extended name is shown depending on the XWU
        Verify.verifySaaSPrice();  // Assert that the Saas Price is calculated correctly
        PlanCalculatorPage.print();
    }

    @Test
    public static void XWayPulseTwinService() {
        PlanCalculatorPage.goToUrl();  // Navigate to Plan Calculator Page
        Verify.verifyHeaderTitle();    //Assert that the expected title matches the actual
        PlanCalculatorPage.clickOnXWayPulseTwin();  //Select X Way Pulse Twin Service from the dropdown
        Verify.verifyServicePlan();  //Assert that the suggested plan matches the selected one
        PlanCalculatorPage.selectMetricUnit();  //Select Metric Unit
        int roadLength = PlanCalculatorPage.setSliderValueRoadLength(10);  //Set value to Road Length Slider
        int numberOfIntersections = PlanCalculatorPage.setSliderValueNumberOfIntersections(10);  //Set value to Number of intersections slider
        Verify.chekExtendedName();  // Assert that the correct extended name is shown depending on the XWU
        Verify.verifySaaSPrice();  // Assert that the Saas Price is calculated correctly
        PlanCalculatorPage.print();

    }

    @Test
    public static void XWayPulseTwinNeuralService() {
        PlanCalculatorPage.goToUrl();  // Navigate to Plan Calculator Page
        Verify.verifyHeaderTitle();    //Assert that the expected title matches the actual
        PlanCalculatorPage.clickOnXWayPulseTwinNeural();  //Select X Way Pulse Twin Neural Service from the dropdown
        Verify.verifyServicePlan();  //Assert that the suggested plan matches the selected one
        PlanCalculatorPage.selectMetricUnit();  //Select Metric Unit
        int roadLength = PlanCalculatorPage.setSliderValueRoadLength(90);  //Set value to Road Length Slider
        int numberOfIntersections = PlanCalculatorPage.setSliderValueNumberOfIntersections(90);  //Set value to Number of intersections slider
        Verify.chekExtendedName();  // Assert that the correct extended name is shown depending on the XWU
        Verify.verifySaaSPrice();  // Assert that the Saas Price is calculated correctly
        PlanCalculatorPage.print();

    }
}
