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

public class Level_07_Switch_Page extends BaseTest {

    WebDriver driver;
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyOrderPageObject myOrderPage;
    MyDashboardPageObject myDashboardPage;
    MyApplicationPageObject myApplicationPage;
    MyProductReviewPageObject myProductReviewPage;
    AccountInformationPageObject accountInformationPageObject;
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
    public void TC_02_Navigate_Page() {
        //My Dashboard -> account information
        accountInformationPageObject = myDashboardPage.openAccountInformationPage();
        //account information  -> My Dashboard
        myDashboardPage = accountInformationPageObject.openMyDashboardPage();
        //My Dashboard -> My order
        myOrderPage = myDashboardPage.openMyOrderPage();
        //My order -> My Applications
        myApplicationPage = myOrderPage.openMyApplicationPage();
        //My Applications -> My Product Review
        myProductReviewPage = myApplicationPage.openMyProductReviewPage();
        //My Product Review -> My order
        myOrderPage = myProductReviewPage.openMyOrderPage();
        //My order -> My Product Review
        myProductReviewPage = myOrderPage.openMyProductReviewPage();
        //My Product Review -> My Application
        myApplicationPage = myProductReviewPage.openMyApplicationPage();

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
