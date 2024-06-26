package seleniumActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class SeleniumActions {
    WebDriver driver;
    Select select;
    TakesScreenshot ts;
    static String fileLocation;
    static int lastRowNum;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static short lastCellNum;
    static XSSFCell cell;
    static DataFormatter formatter;
    static String value;
    static String[][] data;

    static FileInputStream fis;
    static Properties prop;
    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
    }

    public String selectDropDownByVisibleText(WebElement ele, String text)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException {
        Select select = new Select(ele);
        select.selectByVisibleText(text);
        return select.getFirstSelectedOption().getText();
    }

    public String selectDropDownByIndex(WebElement ele, int index)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException{
        select = new Select(ele);
        select.selectByIndex(index);
        return select.getFirstSelectedOption().getText();
    }

    public String selectDropDownByValue(WebElement ele, String val)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException{
        select = new Select(ele);
        select.selectByValue(val);
        return select.getFirstSelectedOption().getText();
    }

    public void click(WebElement ele)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException{
        ele.click();
    }

    public void enterValue(WebElement ele, String data)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException{
        ele.clear();
        ele.sendKeys(data);
    }

    public String getTextUsingAttribute(WebElement ele, String attribute)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException{
        return ele.getAttribute(attribute);
    }

    public String getTextData(WebElement ele)
            throws NoSuchElementException, StaleElementReferenceException, ElementNotInteractableException, WebDriverException{
        return ele.getText();
    }

    public void takeScreenshot(String testCaseName)
            throws WebDriverException, IOException, Exception {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        FileHandler.copy(screenshot, new File(System.getProperty("user.dir")
                + "\\src\\test\\java\\webAutomation\\selenium\\failedSnaps\\" + testCaseName + ".png"));
    }

    public static String[][] fetchDataFromExcel(int sheetNo) throws IOException, FileNotFoundException {
        fileLocation = "./testData/data.xlsx";
        workbook = new XSSFWorkbook(fileLocation);
        sheet = workbook.getSheetAt(sheetNo);
        lastRowNum = sheet.getLastRowNum();
        lastCellNum = sheet.getRow(0).getLastCellNum();
        data = new String[lastRowNum][lastCellNum];
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < lastCellNum; j++) {
                cell = row.getCell(j);
                formatter = new DataFormatter();
                value = formatter.formatCellValue(cell);
                data[i - 1][j] = value;
            }
        }
        workbook.close();
        return data;
    }
}
