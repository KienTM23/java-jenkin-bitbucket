package pageObjects.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminManageCustomerPageUI;

public class AdminManageCustomerPageObject extends BasePage {
    WebDriver driver;

    public AdminManageCustomerPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void closeIncomingMessagePopup() {
        waitForElementVisible(driver, AdminManageCustomerPageUI.INCOMING_MESSAGE_POPUP);
        waitForElementClickable(driver,AdminManageCustomerPageUI.INCOMING_MESSAGE_POPUP_CLOSE_ICON);
        clickToElement(driver,AdminManageCustomerPageUI.INCOMING_MESSAGE_POPUP_CLOSE_ICON);
    }

}
