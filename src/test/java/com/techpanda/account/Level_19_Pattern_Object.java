package com.techpanda.account;

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


public class Level_19_Pattern_Object extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        showBrowserConsoleLogs();
        homePage = PageGeneratorManager.getUserHomePage(driver);

        emailAdress = "automation@gmail.com";
        password = "123456";
    }

    @Test
    public void TC_01_Login_Empty_Email_And_Password() {
        log.info("Login_01 - Step 01: click to My Account link");
        loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();
        showBrowserConsoleLogs();
        log.info("Login_01 - Step 02: Enter to Email Address textbox with empty data");
        loginPage.enterToTextboxByID(driver, "email", "");

        log.info("Login_01 - Step 03: Enter to Password with empty data");
        loginPage.enterToTextboxByID(driver, "pass", "");

        log.info("Login_01 - Step 04: Click to Login button");
        loginPage.clickToLoginButton();
        showBrowserConsoleLogs();
        log.info("Login_01 - Step 05: Verify error message is displayed");

        Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-required-entry-email"), "This is a required field.");
        Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-required-entry-pass"), "This is a required field.");
    }

    @Test(dependsOnMethods = "TC_01_Login_Empty_Email_And_Password")
    public void TC_02_Login_Invalid_Email() {
        log.info("Login_02 - Step 01: click to My Account link");
        loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();

        log.info("Login_02 - Step 02: Enter to Email Address textbox ");
        loginPage.enterToTextboxByID(driver, "email", "123@456.789");

        log.info("Login_02 - Step 03: Enter to Password textbox ");
        loginPage.enterToTextboxByID(driver, "pass", "123456");

        log.info("Login_02 - Step 04: click to Login Button");
        loginPage.clickToLoginButton();

        log.info("Login_02 - Step 05: Verify the error message is displayed");

        Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-validate-email-email"), "Please enter a valid email address. For example johndoe@domain.com.");
    }


    @Test(description = "Email not exist in application")
    public void TC_03_Login_Incorrect_Email() {
        log.info("Login_03 - Step 01: click to My Account link");
        loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();

        log.info("Login_03 - Step 02: Enter to Email Address textbox ");
        loginPage.enterToTextboxByID(driver, "email", "auto_test" + getRandomNumber() + "@live.com");

        log.info("Login_03 - Step 03: Enter to Password textbox ");
        loginPage.enterToTextboxByID(driver, "pass", "2222");

        log.info("Login_03 - Step 04: click to Login Button");
        loginPage.clickToButtonByTitle(driver, "Login");

        log.info("Login_03 - Step 05: Verify the error message is displayed");
        Assert.assertEquals(loginPage.getPageErrorMessage(driver), "Invalid login or password.");

    }


    @Test(description = "Password less than 6 characters")
    public void TC_04_Login_Invalid_Password() {
        log.info("Login_04 - Step 01: click to My Account link");
        loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.enterToTextboxByID(driver, "email", "auto_test" + getRandomNumber() + "@live.com");

        loginPage.inputToPasswordTextbox("123");
        loginPage.clickToButtonByTitle(driver, "Login");

        Assert.assertEquals(loginPage.getPassWordInvalidErrorMessage(), "Please enter 6 or more characters without leading or trailing spaces.");
        Assert.assertEquals(loginPage.getFieldErrorMessageByID(driver, "advice-validate"), "Invalid login or password.");
    }

    @Test
    public void TC_05_Login_Incorrect_Password() {
        log.info("Login_05 - Step 01: click to My Account link");
        loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();

        loginPage.inputToEmailAddressTextbox("auto_test" + getRandomNumber() + "@live.com");
        loginPage.enterToTextboxByID(driver, "email", "auto_test" + getRandomNumber() + "@live.com");

        loginPage.inputToPasswordTextbox((String.valueOf(getRandomNumber())));
        loginPage.enterToTextboxByID(driver, "pass", (String.valueOf(getRandomNumber())));

        loginPage.clickToButtonByTitle(driver, "Login");

        Assert.assertEquals(loginPage.getEmailPasswordIncorrectErrorMessage(), "Invalid login or password.");
    }

    @Test
    public void TC_06_Login_Valid_Email_And_Password() {
        log.info("Login_06 - Step 01: click to My Account link");
        loginPage = homePage.getFooterContainerPage(driver).openMyAccountPageNotLogged();

        log.info("Login_06 - Step 02: Enter to Email Address textbox with value " + emailAdress);
        loginPage.inputToEmailAddressTextbox(emailAdress);
        loginPage.enterToTextboxByID(driver, "email", emailAdress);


        log.info("Login_06 - Step 03: Enter to Email Password with value " + password);
        loginPage.enterToTextboxByID(driver, "pass", password);


        loginPage.clickToButtonByTitle(driver, "Login");
        myDashboardPage = PageGeneratorManager.getMyDashboardPage(driver);

        log.info("Login_06 - Step 05: Verify Contact is displayed");

        Assert.assertFalse(myDashboardPage.isContactInforDisplayed("Phuong Phung"));
        Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation@gmail.com"));
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    UserHomePageObject homePage;
    UserLoginPageObject loginPage;
    MyDashboardPageObject myDashboardPage;
    AccountInformationPageObject accountInformationPageObject;
    String emailAdress, password;

}
