package com.techpanda.account;

import com.aventstack.extentreports.Status;
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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;


public class Level_16_Extent_Report extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        this.browserName = browserName.toUpperCase();
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);

        emailAdress = "automation@gmail.com";
        password = "123456";
    }

    @Test
    public void TC_01_Login_Empty_Email_And_Password(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName,"TC_01_Login_Empty_Email_And_Password");
        ExtentManager.getTest().log(Status.INFO,"Login_01 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        ExtentManager.getTest().log(Status.INFO,"Login_01 - Step 02: Enter to Email Address textbox with empty data");
        loginPage.inputToEmailAddressTextbox("");

        ExtentManager.getTest().log(Status.INFO,"Login_01 - Step 03: Enter to Password with empty data");
        loginPage.inputToPasswordTextbox("");

        ExtentManager.getTest().log(Status.INFO,"Login_01 - Step 04: Click to Login button");
        loginPage.clickToLoginButton();

        ExtentManager.getTest().log(Status.INFO,"Login_01 - Step 05: Verify error message is displayed");
        Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(), "This is a required field.");
        Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
    }

    @Test
    public void TC_02_Login_Invalid_Email(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName,"TC_02_Login_Invalid_Email");
        ExtentManager.getTest().log(Status.INFO,"Login_02 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        ExtentManager.getTest().log(Status.INFO,"Login_02 - Step 02: Enter to Email Address textbox ");
        loginPage.inputToEmailAddressTextbox("123@456.789");

        ExtentManager.getTest().log(Status.INFO,"Login_02 - Step 03: Enter to Password textbox ");
        loginPage.inputToPasswordTextbox("123456");

        ExtentManager.getTest().log(Status.INFO,"Login_02 - Step 04: click to Login Button");
        loginPage.clickToLoginButton();

        ExtentManager.getTest().log(Status.INFO,"Login_02 - Step 05: Verify the error message is displayed");
        Assert.assertEquals(loginPage.getEmailAddressInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test(description = "Email not exist in application")
    public void TC_03_Login_Incorrect_Email(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName,"TC_03_Login_Incorrect_Email");
        ExtentManager.getTest().log(Status.INFO,"Login_03 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        ExtentManager.getTest().log(Status.INFO,"Login_03 - Step 02: Enter to Email Address textbox ");
        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");

        ExtentManager.getTest().log(Status.INFO,"Login_03 - Step 03: Enter to Password textbox ");
        loginPage.inputToPasswordTextbox("123456");

        ExtentManager.getTest().log(Status.INFO,"Login_03 - Step 04: click to Login Button");
        loginPage.clickToLoginButton();

        ExtentManager.getTest().log(Status.INFO,"Login_03 - Step 05: Verify the error message is displayed");
        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }

    @Test(description = "Password less than 6 characters")
    public void TC_04_Login_Invalid_Password(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName,"TC_04_Login_Invalid_Password");
        ExtentManager.getTest().log(Status.INFO,"Login_04 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox("123");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getPassWordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_05_Login_Incorrect_Password(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName,"TC_05_Login_Incorrect_Password");
        ExtentManager.getTest().log(Status.INFO,"Login_05 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox((String.valueOf(getRandomNumber())));
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }

    @Test
    public void TC_06_Login_Valid_Email_And_Password(Method method) {
        ExtentManager.startTest(method.getName() + "-" + browserName,"TC_06_Login_Valid_Email_And_Password");
        ExtentManager.getTest().log(Status.INFO,"Login_06 - Step 01: click to My Account link");
        loginPage = homePage.clickToMyAccountLink();

        ExtentManager.getTest().log(Status.INFO,"Login_06 - Step 02: Enter to Email Address textbox with value "+ emailAdress);
        loginPage.inputToEmailAddressTextbox(emailAdress);

        ExtentManager.getTest().log(Status.INFO,"Login_06 - Step 03: Enter to Email Password with value "+ password);
        loginPage.inputToPasswordTextbox(password);

        myDashboardPage = loginPage.clickToLoginButton();

        ExtentManager.getTest().log(Status.INFO,"Login_06 - Step 05: Verify Contact is displayed");
        Assert.assertFalse(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));
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
    String emailAdress, password,browserName;

}
