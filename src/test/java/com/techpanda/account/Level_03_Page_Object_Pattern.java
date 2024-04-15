package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;

import java.time.Duration;
import java.util.Random;

public class Level_03_Page_Object_Pattern{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyDashboardPageObject myDashboardPage;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.get("http://live.techpanda.org/");

        //1- mo url qua trang homepage
        //2- muon dung duoc class HomePageObject thi phai khoi tao len
        homePage = new UserHomePageObject(driver);
    }
    @Test
    public void TC_01_Login_Empty_Email_And_Password() {
        //home click My Account link -> LoginPage
        homePage.clickToMyAccountLink();

        loginPage = new UserLoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("");
        loginPage.inputToPasswordTextbox("");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMessage(),"This is a required field.");
        Assert.assertEquals(loginPage.getPasswordEmptyErrorMessage(),"This is a required field.");
    }

    @Test
    public void TC_02_Login_Invalid_Email() {
        homePage.clickToMyAccountLink();

        loginPage = new UserLoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("123@456.789");
        loginPage.inputToPasswordTextbox("123456");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailAddressInvalidErrorMessage(), "Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test(description = "Email not exist in application")
    public void TC_03_Login_Incorrect_Email() {
        homePage.clickToMyAccountLink();

        loginPage = new UserLoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("auto_test" + randomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox("123456");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }
    @Test(description = "Password less than 6 characters")
    public void TC_04_Login_Invalid_Password() {
        homePage.clickToMyAccountLink();

        loginPage = new UserLoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("auto_test" + randomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox("123");
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getPassWordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
    }
    @Test
    public void TC_05_Login_Incorrect_Password() {
        homePage.clickToMyAccountLink();

        loginPage = new UserLoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("auto_test" + randomNumber() + "@live.com");
        loginPage.inputToPasswordTextbox((String.valueOf(randomNumber())));
        loginPage.clickToLoginButton();

        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }
    @Test
    public void TC_06_Login_Valid_Email_And_Password() {
        homePage.clickToMyAccountLink();

        loginPage = new UserLoginPageObject(driver);

        loginPage.inputToEmailAddressTextbox("automation@gmail.com");
        loginPage.inputToPasswordTextbox("123456");
        loginPage.clickToLoginButton();

        myDashboardPage = new MyDashboardPageObject(driver);

        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(999999);
    }
}
