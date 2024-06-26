package seleniumTestCases;

import baseTest.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import seleniumActions.SeleniumActions;
import seleniumPages.ServicenowHomePage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ServicenowPageLoginValidation extends BaseTest {
    String[][] fetchDataFromExcel;

    @Test(priority = 2,dataProvider = "login")
    public void loginServicenowValidation(String email,String pwd){
        ServicenowHomePage homePage=new ServicenowHomePage(driver);
        homePage.enterEmailForLogin(email).enterPasswordForLogin(pwd).validateLoginSuccess();
    }

    @DataProvider(name = "login")
    public Object[][] fetchData() throws FileNotFoundException, IOException {
        fetchDataFromExcel = SeleniumActions.fetchDataFromExcel(1);
        return fetchDataFromExcel;
    }
}
