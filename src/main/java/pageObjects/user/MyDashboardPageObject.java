package pageObjects.user;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.navigation.SideBarMyAccountPageObject;
import pageUIs.user.MyDashboardPageUI;

public class MyDashboardPageObject extends SideBarMyAccountPageObject {

    private WebDriver driver;

    public MyDashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    @Step("Verify contact informatiom is displayed with value {0}")
    public boolean isContactInforDisplayed(String contactInfor) {
        waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
        String actualContactInforText = getElementText(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
        return actualContactInforText.contains(contactInfor);
    }
    @Step("Verify Account informatiom is displayed")
    public boolean isAccountInformationMessageSavedDisplayed() {
        waitForElementVisible(driver, MyDashboardPageUI.ACCOUNT_INFOR_SAVED_MESSAGE);
        return isElementDisplayedInDOM(driver, MyDashboardPageUI.ACCOUNT_INFOR_SAVED_MESSAGE);
    }


}
