package com.techpanda.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminManageCustomerPageObject;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.*;

public class Level_09_Switch_Role extends BaseTest {

    WebDriver driver;
    UserHomePageObject userHomePageObject;
    UserLoginPageObject userLoginPageObject;
    AdminManageCustomerPageObject adminHomePageObject;
    AdminLoginPageObject adminLoginPageObject;
    MyOrderPageObject myOrderPage;
    MyDashboardPageObject myDashboardPage;
    MyApplicationPageObject myApplicationPage;
    MyProductReviewPageObject myProductReviewPage;
    AccountInformationPageObject accountInformationPageObject;
    AdminManageCustomerPageObject adminManageCustomerPageObject;
    String userUrl, adminUrl;

    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl) {
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;
        driver = getBrowserDriver(browserName, userUrl);
        userHomePageObject = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    public void TC_01_switch_Role() {
        userLoginPageObject = userHomePageObject.clickToMyAccountLink();
        userLoginPageObject.inputToEmailAddressTextbox("automation@gmail.com");
        userLoginPageObject.inputToPasswordTextbox("123456");

        myDashboardPage = userLoginPageObject.clickToLoginButton();
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        //Logout
        userHomePageObject = myDashboardPage.clickToUserLogoutLink(driver);
        //User -> Admin -> LoginAdmin
        adminLoginPageObject = userHomePageObject.openAdminLoginPage(driver,adminUrl);

        adminLoginPageObject.enterToUserNameTextox("user01");
        adminLoginPageObject.enterToPasswordTextox("guru99com");
        adminManageCustomerPageObject = adminLoginPageObject.clickToLoginButton();

        adminManageCustomerPageObject.closeIncomingMessagePopup();

        adminLoginPageObject = adminManageCustomerPageObject.clickToAdminLogoutLink(driver);
        //Admin -> User
        userHomePageObject = adminLoginPageObject.openUserHomePage(driver,userUrl);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
