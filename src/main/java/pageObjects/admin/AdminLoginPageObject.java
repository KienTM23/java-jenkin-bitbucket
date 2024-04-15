package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.navigation.PageGeneratorManager;
import pageUIs.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage {
    WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //Action
    public AdminManageCustomerPageObject clickToLoginButton(){
        waitForElementClickable(driver,AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getAdminManageCustomerPage(driver);
    }

    public void enterToUserNameTextox(String username) {
        waitForElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXTBOX);
        sendkeyToElement(driver,AdminLoginPageUI.USER_NAME_TEXTBOX,username);
    }

    public void enterToPasswordTextox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver,AdminLoginPageUI.PASSWORD_TEXTBOX,password);
    }
}
