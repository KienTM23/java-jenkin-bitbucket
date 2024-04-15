package pageObjects.navigation;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.user.*;
import pageUIs.navigation.SideBarMyAccountPageUI;

public class SideBarMyAccountPageObject extends BasePage {
    WebDriver driver;

    public SideBarMyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public MyApplicationPageObject openMyApplicationPage() {
        waitForElementClickable(driver, SideBarMyAccountPageUI.MY_APPLICATION_LINK);
        clickToElement(driver, SideBarMyAccountPageUI.MY_APPLICATION_LINK);
        return PageGeneratorManager.getMyApplicationPage(driver);
    }

    public MyOrderPageObject openMyOrderPage() {
        waitForElementClickable(driver, SideBarMyAccountPageUI.MY_ORDER_LINK);
        clickToElement(driver, SideBarMyAccountPageUI.MY_ORDER_LINK);
        return PageGeneratorManager.getMyOrderPage(driver);
    }

    public AccountInformationPageObject openAccountInformationPage() {
        waitForElementClickable(driver, SideBarMyAccountPageUI.ACCOUNT_INFORMATION_LINK);
        clickToElement(driver, SideBarMyAccountPageUI.ACCOUNT_INFORMATION_LINK);
        return PageGeneratorManager.getAccountInformationPage(driver);
    }

    public MyDashboardPageObject openMyDashboardPage() {
        waitForElementClickable(driver, SideBarMyAccountPageUI.MY_DASHBOARD_LINK);
        clickToElement(driver, SideBarMyAccountPageUI.MY_DASHBOARD_LINK);
        return PageGeneratorManager.getMyDashboardPage(driver);
    }

    public MyProductReviewPageObject openMyProductReviewPage() {
        waitForElementClickable(driver, SideBarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
        clickToElement(driver, SideBarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
        return PageGeneratorManager.getMyProductReviewPage(driver);
    }

    //ko return ve bat ki 1 page nao
    public void openSideBarLinkByPageName(String pageName) {
        waitForElementClickable(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, pageName);
        clickToElement(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, pageName);
    }

    //return : dung cho so luong it page
    public SideBarMyAccountPageObject openSideBarLinkByPageNames(String pageName) {
        waitForElementClickable(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, pageName);
        clickToElement(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, pageName);

        if (pageName.equals("My Applications")) {
            return PageGeneratorManager.getMyApplicationPage(driver);
        } else if (pageName.equals("My Orders")) {
            return PageGeneratorManager.getMyOrderPage(driver);
        } else if (pageName.equals("Account Information")) {
            return PageGeneratorManager.getAccountInformationPage(driver);
        } else if (pageName.equals("My Product Reviews")) {
            return PageGeneratorManager.getMyProductReviewPage(driver);
        } else {
            return null;
        }
    }
}
