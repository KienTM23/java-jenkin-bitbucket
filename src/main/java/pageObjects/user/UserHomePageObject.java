package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.navigation.PageGeneratorManager;
import pageUIs.user.HomePageUI;

public class UserHomePageObject extends BasePage {
    //ham khoi tao - no dc goi dau tien khi khoi tao 1 class len
    // cung ten vs ten class
    // ko co kieu tra ve
    // co tham so hoac ko co tham so

    private WebDriver driver;
    //Map driver
    public UserHomePageObject(WebDriver driver){
        this.driver = driver;
    }

    @Step("Click to My Account Link")
    public UserLoginPageObject   clickToMyAccountLink() {
        waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver,HomePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }
}
