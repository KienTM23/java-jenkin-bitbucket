package pageObjects.navigation;

import org.openqa.selenium.WebDriver;
import pageObjects.JQuery.HomePageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminManageCustomerPageObject;
import pageObjects.user.*;

public class PageGeneratorManager {
    public static UserHomePageObject getUserHomePage(WebDriver driver){
        return new UserHomePageObject(driver);
    }
    public static UserLoginPageObject getUserLoginPage(WebDriver driver){
        return new UserLoginPageObject(driver);
    }
    public static MyDashboardPageObject getMyDashboardPage(WebDriver driver){
        return new MyDashboardPageObject(driver);
    }
    public static AccountInformationPageObject getAccountInformationPage(WebDriver driver){
        return new AccountInformationPageObject(driver);
    }
    public static MyApplicationPageObject getMyApplicationPage(WebDriver driver){
        return new MyApplicationPageObject(driver);
    }
    public static MyOrderPageObject getMyOrderPage(WebDriver driver){
        return new MyOrderPageObject(driver);
    }
    public static MyProductReviewPageObject getMyProductReviewPage(WebDriver driver){
        return new MyProductReviewPageObject(driver);
    }
    public static AboutUsPageObject getAboutUsPage(WebDriver driver){
        return new AboutUsPageObject(driver);
    }
    public static SearchTermPageObject getSearchTermPage(WebDriver driver){
        return new SearchTermPageObject(driver);
    }
    public static MyAccountPageObject getMyAccountPage(WebDriver driver){
        return new MyAccountPageObject(driver);
    }
    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver){
        return new AdminLoginPageObject(driver);
    }
    public static AdminManageCustomerPageObject getAdminManageCustomerPage(WebDriver driver){
        return new AdminManageCustomerPageObject(driver);
    }

    public static HomePageObject getHomePage(WebDriver driver){
        return new HomePageObject(driver);
    }
}
