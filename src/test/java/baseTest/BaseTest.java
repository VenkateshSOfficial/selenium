package baseTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import seleniumActions.SeleniumActions;


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
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
            }

            driver.navigate().to(prop.getProperty("url"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("WebDriverException occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

}
