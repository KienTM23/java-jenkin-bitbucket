package com.techpanda.cookie;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;


public class Payment_02_Checkout extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);

        emailAdress = "automation@gmail.com";
        password = "123456";

        loginPage = homePage.clickToMyAccountLink();
        //login
        loginPage.setCookies(driver, Common_Login.loginCookies);

        myDashboardPage = PageGeneratorManager.getMyDashboardPage(driver);

        Assert.assertFalse(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));
    }

    @Test
    public void Order_01() {

    }
    @Test
    public void Order_02() {

    }
    @Test
    public void Order_03() {

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyDashboardPageObject myDashboardPage;
    AccountInformationPageObject accountInformationPageObject;
    String emailAdress, password;

}
