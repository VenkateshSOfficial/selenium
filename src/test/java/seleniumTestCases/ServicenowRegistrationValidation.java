package seleniumTestCases;

import baseTest.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import seleniumActions.SeleniumActions;
import seleniumPages.ServicenowHomePage;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ServicenowRegistrationValidation extends BaseTest {
    String[][] fetchDataFromExcel;
    @Test(priority=1,dataProvider = "data")
    public void register(String fName,String lName,String email,String pwd,String confirmPwd) throws InterruptedException {
        ServicenowHomePage homePage=new ServicenowHomePage(driver);
        try{
            homePage.clickRegisterButton().
                    enterFirstName(fName).
                    enterLastName(lName).
                    enterEmailAddress(email).
                    enterPassword(pwd).
                    enterConfirmPassword(confirmPwd).selectCountry().termsAndConditionsCheckBox();
        }catch (Exception ae){
            System.err.println(ae);
        }
    }

    @DataProvider(name = "data")
    public Object[][] fetchData() throws FileNotFoundException, IOException {
        fetchDataFromExcel = SeleniumActions.fetchDataFromExcel(0);
        return fetchDataFromExcel;
    }
}
