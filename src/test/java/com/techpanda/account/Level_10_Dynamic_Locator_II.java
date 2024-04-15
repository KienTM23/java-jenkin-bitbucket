package com.techpanda.account;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.*;

public class Level_10_Dynamic_Locator_II extends BaseTest {

    WebDriver driver;
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyOrderPageObject myOrderPage;
    MyDashboardPageObject myDashboardPage;
    MyApplicationPageObject myApplicationPage;
    MyProductReviewPageObject myProductReviewPage;
    AboutUsPageObject aboutUsPage;
    SearchTermPageObject searchTermPage;
    MyAccountPageObject myAccountPage;
    AccountInformationPageObject accountInformationPage;
    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    public void TC_01_Login_Valid_Email_And_Password() {
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("automation@gmail.com");
        loginPage.inputToPasswordTextbox("123456");

        myDashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));
    }

    @Test
    public void TC_02_Sidebar_Page() {
        //My Dashboard -> account information
        //A- B
        //B khoi tao
        accountInformationPage = (AccountInformationPageObject) myDashboardPage.openSideBarLinkByPageNames("Account Information");
        //account information  -> My Dashboard
        myDashboardPage = (MyDashboardPageObject) accountInformationPage.openSideBarLinkByPageNames("Account Dashboard");
        //My Dashboard -> My order
        myOrderPage = (MyOrderPageObject) myDashboardPage.openSideBarLinkByPageNames("My Orders");

        //My order -> My Applications
        myApplicationPage = (MyApplicationPageObject) myOrderPage.openSideBarLinkByPageNames("My Applications");
        //....
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
