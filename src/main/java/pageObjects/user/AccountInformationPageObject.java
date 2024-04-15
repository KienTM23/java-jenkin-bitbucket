package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.navigation.SideBarMyAccountPageObject;
import pageUIs.user.AccountInformationPageUI;

public class AccountInformationPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;

    public AccountInformationPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, AccountInformationPageUI.FIRSTNAME_TEXTBOX);
        sendkeyToElement(driver,AccountInformationPageUI.FIRSTNAME_TEXTBOX,firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, AccountInformationPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,AccountInformationPageUI.PASSWORD_TEXTBOX,lastName);
    }

    public void enterToEmailAddressTextbox(String emailAddress) {
        waitForElementVisible(driver, AccountInformationPageUI.EMAIL_ADDRES_TEXTBOX);
        sendkeyToElement(driver,AccountInformationPageUI.EMAIL_ADDRES_TEXTBOX,emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AccountInformationPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,AccountInformationPageUI.PASSWORD_TEXTBOX,password);
    }

    public MyDashboardPageObject clickToSaveButton() {
        waitForElementClickable(driver,AccountInformationPageUI.SAVE_BUTTON);
        clickToElement(driver,AccountInformationPageUI.SAVE_BUTTON);
        return PageGeneratorManager.getMyDashboardPage(driver);
    }


}
