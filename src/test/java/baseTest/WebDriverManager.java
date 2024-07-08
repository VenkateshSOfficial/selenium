package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    public static WebDriverManager driverManager;
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private WebDriverManager() {

    }

    public void initiateDriver(String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            tlDriver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            tlDriver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("edge")) {
            tlDriver.set(new EdgeDriver());
        } else {
            throw new Exception("invalid driver input");
        }
    }

    public static WebDriverManager getDriverInstance(String browser) throws Exception {
        if (driverManager == null) {
            synchronized (WebDriverManager.class) {
                if (driverManager == null) {
                    driverManager = new WebDriverManager();
                }
            }
        }
        if (tlDriver.get() == null) {
            driverManager.initiateDriver(browser);
        }
        return driverManager;
    }

    public WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitBrowser() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
