package seleniumTestCases;

import baseTest.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import seleniumPages.ServicenowHomePage;


public class ServicenowPageValidation extends BaseTest {
    @Parameters({"titleValidation"})
    @Test(priority=0)
    public void validateServicenowPage(String title){
        ServicenowHomePage homePage=new ServicenowHomePage(driver);
        homePage.validateServicenowPage(title);
    }
}
