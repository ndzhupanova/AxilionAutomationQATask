package Base;

import Utils.BrowserActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void browserSetUp(){
        BrowserActions.setup();
    }
    @AfterClass
   public static void browserTearDown(){
        BrowserActions.tearDown();
   }
}