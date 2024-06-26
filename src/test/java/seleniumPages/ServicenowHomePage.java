package seleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import seleniumActions.SeleniumActions;


public class ServicenowHomePage extends SeleniumActions {

    WebDriver driver;

    public ServicenowHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Get a ServiceNow ID")
    private WebElement registerButton;

    @FindBy(css = "#firstName")
    private WebElement firstName;

    @FindBy(css = "#lastName")
    private WebElement lastName;

    @FindBy(css = ".UF-email-validation")
    private WebElement email;

    @FindBy(css = "#password")
    private WebElement password;

    @FindBy(css = "#confirmPassword")
    private WebElement confirmPassword;

    @FindBy(css = "#country")
    private WebElement countryDropDown;

    @FindBy(css = ".UF-check")
    private WebElement termsAndConditionsCheckBox;

    @FindBy(css = "#username_submit_button")
    private WebElement emailSubmitButton;

    @FindBy(css = "#password_submit_button")
    private WebElement passwordSubmitButton;


    public void validateServicenowPage(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }

    public ServicenowHomePage clickRegisterButton() {
        click(registerButton);
        return this;
    }

    public ServicenowHomePage enterFirstName(String fName) {
        enterValue(firstName, fName);
        return this;
    }

    public ServicenowHomePage enterLastName(String lName) {
        enterValue(lastName, lName);
        return this;
    }

    public ServicenowHomePage enterEmailAddress(String mail) {
        enterValue(email, mail);
        return this;
    }

    public ServicenowHomePage enterPassword(String pwd) {
        enterValue(password, pwd);
        return this;
    }

    public ServicenowHomePage enterConfirmPassword(String confirmPwd) {
        enterValue(confirmPassword, confirmPwd);
        return this;
    }

    public ServicenowHomePage selectCountry() {
        Assert.assertEquals(selectDropDownByValue(countryDropDown, "IN"), "IN - India");
        return this;
    }

    public ServicenowHomePage termsAndConditionsCheckBox() throws InterruptedException {
        click(termsAndConditionsCheckBox);
        return this;
    }

    public ServicenowHomePage enterEmailForLogin(String loginEmail) {
        enterValue(email, loginEmail);
        click(emailSubmitButton);
        return this;
    }

    public ServicenowHomePage enterPasswordForLogin(String loginPassword) {
        enterValue(password, loginPassword);
        click(passwordSubmitButton);
        return this;
    }
    public void validateLoginSuccess(){
        Assert.assertEquals(driver.getCurrentUrl(),"https://signon.service-now.com/x_snc_sso_auth.do?pageId=password");

    }
}
