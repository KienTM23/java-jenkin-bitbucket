package pageObjects.facebook;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToCreateNewAccountButton(){
        clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
    }
    public void enterToEmailTextbox(String emailAddress){
        sendkeyToElement(driver,LoginPageUI.EMAIL_TEXTBOX,emailAddress);
    }
    public void waitForReEnterEmailTextboxVisible(){
        waitForElementVisible(driver,LoginPageUI.RE_ENTER_EMAIL_TEXT_BOX);
    }
    public boolean isReEnterEmailTextboxDisplayed(){
       return isElementDisplayedInDOM(driver,LoginPageUI.RE_ENTER_EMAIL_TEXT_BOX);
    }
    public boolean isReEnterEmailTextboxUndisplayed(){
        return isElementUndisplayed(driver,LoginPageUI.RE_ENTER_EMAIL_TEXT_BOX);
    }
    public void waitForReEnterEmailTextboxInvisibleInDOM(){
        waitForElementInVisibleInDOM(driver,LoginPageUI.RE_ENTER_EMAIL_TEXT_BOX);
    }
    public void waitForReEnterEmailTextboxInvisibleNotInDOM(){
        waitForElementInVisibleNotInDOM(driver,LoginPageUI.RE_ENTER_EMAIL_TEXT_BOX);
    }
    public void clickToCloseIconSignUpForm(){
        clickToElement(driver,LoginPageUI.CLOSE_ICON_SIGNUP_FORM);
    }
}
