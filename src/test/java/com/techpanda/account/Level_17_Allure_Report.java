package com.techpanda.account;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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


public class Level_17_Allure_Report extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getUserHomePage(driver);

        emailAdress = "automation@gmail.com";
        password = "123456";
    }

    @Description("Login with empty Email and Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_01_Login_Empty_Email_And_Password() {
       
        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("");


        loginPage.inputToPasswordTextbox("");

        loginPage.clickToLoginButton();


        Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(), "This is a required field.");
        Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(), "This is a required field.");
    }
    @Description("Login with invalid Email")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_02_Login_Invalid_Email() {

        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("123@456.789");


        loginPage.inputToPasswordTextbox("123456");


        loginPage.clickToLoginButton();


        Assert.assertEquals(loginPage.getEmailAddressInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Description("Login with incorrect Email and Password")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Email not exist in application")
    public void TC_03_Login_Incorrect_Email() {
       
        loginPage = homePage.clickToMyAccountLink();


        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");


        loginPage.inputToPasswordTextbox("123456");


        loginPage.clickToLoginButton();


        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }

    @Description("Login with invalid Password")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "Password less than 6 characters")
    public void TC_04_Login_Invalid_Password() {

        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox("123");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getPassWordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Description("Login with incorrect Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_05_Login_Incorrect_Password() {

        loginPage = homePage.clickToMyAccountLink();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox((String.valueOf(getRandomNumber())));
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }
    @Description("Login with valid Email and Password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void TC_06_Login_Valid_Email_And_Password() {

        loginPage = homePage.clickToMyAccountLink();


        loginPage.inputToEmailAddressTextbox(emailAdress);


        loginPage.inputToPasswordTextbox(password);

        myDashboardPage = loginPage.clickToLoginButton();


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
    String emailAdress, password;

}
