package baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


public class BaseTest {
    public WebDriver driver;
    FileInputStream fis;

    Properties prop;

    public void readConfigFile() throws IOException {
        fis = new FileInputStream(System.getProperty("user.dir")
                + "\\src\\test\\java\\seleniumResources\\globalfile.properties");
        prop = new Properties();
        prop.load(fis);
    }
    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public void launchBrowser(String browserName) throws IOException {
        readConfigFile();
        try {
            driver= WebDriverManager.getDriverInstance(browserName).getDriver();
            driver.navigate().to(prop.getProperty("url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        WebDriverManager.quitBrowser();
    }
}
