package com.techpanda.cookie;

import commons.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;

import java.util.Set;


public class Common_Login extends BaseTest {

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);

        emailAdress = "automation@gmail.com";
        password = "123456";

        log.info("Login_06 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        log.info("Login_06 - Step 02: Enter to Email Address textbox with value " + emailAdress);
        loginPage.inputToEmailAddressTextbox(emailAdress);

        log.info("Login_06 - Step 03: Enter to Email Password with value " + password);
        loginPage.inputToPasswordTextbox(password);

        myDashboardPage = loginPage.clickToLoginButton();

        log.info("Login_06 - Step 05: Verify Contact is displayed");
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));

        //lay thong tin cookie ra
        loginCookies = myDashboardPage.getCookies(driver);

        driver.quit();
    }

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyDashboardPageObject myDashboardPage;
    String emailAdress, password;
    static  Set<Cookie> loginCookies;

}
