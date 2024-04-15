package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.navigation.PageGeneratorManager;
import pageUIs.user.LoginPageUI;

public class UserLoginPageObject extends BasePage {
    private WebDriver driver;
    public UserLoginPageObject(WebDriver driver){
        this.driver = driver;
    }
    @Step("Enter to Email Address textbox with value {0}")
    public void inputToEmailAddressTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver,LoginPageUI.EMAIL_ADDRESS_TEXTBOX,emailAddress);
    }
    @Step("Enter to Password textbox with value {0}")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,LoginPageUI.PASSWORD_TEXTBOX,password);
    }
    @Step("Enter to Login Button")
    public MyDashboardPageObject clickToLoginButton() {
        waitForElementClickable(driver,LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getMyDashboardPage(driver);
    }
    @Step("Get Email Address empty error message")
    public String getEmailAddressEmptyErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MESSAGE);
    }
    @Step("Get Password empty error message")
    public String getPasswordEmptyErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE);
    }

    public String getEmailAddressInvalidErrorMessage() {
       waitForElementVisible(driver,LoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MESSAGE);
       return getElementText(driver,LoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MESSAGE);
    }

    public String getEmailPasswordIncorrectErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MESSAGE);
    }

    public String getPassWordInvalidErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE);
    }
}
