package pageObjects.navigation;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.SearchTermPageObject;
import pageObjects.user.UserLoginPageObject;
import pageUIs.navigation.FooterContainerPageUI;

public class FooterContainerPageObject extends BasePage {
    WebDriver driver;

    public FooterContainerPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public AboutUsPageObject openAboutUsPage(){
        waitForElementClickable(driver, FooterContainerPageUI.ABOUT_US_LINK);
        clickToElement(driver,FooterContainerPageUI.ABOUT_US_LINK);
        return PageGeneratorManager.getAboutUsPage(driver);
    }
    public SearchTermPageObject openSearchTermPage(){
        waitForElementClickable(driver, FooterContainerPageUI.SEARCH_TERMS_LINK);
        clickToElement(driver,FooterContainerPageUI.SEARCH_TERMS_LINK);
        return PageGeneratorManager.getSearchTermPage(driver);
    }
    public MyAccountPageObject openMyAccountPageLogged(){
        waitForElementClickable(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
        clickToElement(driver,FooterContainerPageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getMyAccountPage(driver);
    }
    public UserLoginPageObject openMyAccountPageNotLogged(){
        waitForElementClickable(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
        clickToElement(driver,FooterContainerPageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }
}
