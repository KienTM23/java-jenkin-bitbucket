package com.facebook;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

import java.util.Random;

public class Level_18_Element_Undisplayed extends BaseTest {
    WebDriver driver;
    LoginPageObject loginPage;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String appUrl) {
        driver = getBrowserDriver(browserName, appUrl);
        loginPage = PageGeneratorManager.getLoginPage(driver);
    }

    @Test
    public void TC_01_Visible() {
        loginPage.clickToCreateNewAccountButton();

        loginPage.enterToEmailTextbox("automation@gmail.com");

        loginPage.waitForReEnterEmailTextboxVisible();

        Assert.assertTrue(loginPage.isReEnterEmailTextboxDisplayed());
    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        loginPage.enterToEmailTextbox("");

        loginPage.waitForReEnterEmailTextboxInvisibleInDOM();

        Assert.assertTrue(loginPage.isReEnterEmailTextboxUndisplayed());
    }

    @Test
    public void TC_03_Invisible_Not_In_DOM() {
        loginPage.clickToCloseIconSignUpForm();
        loginPage.sleepInSecond(2);
        loginPage.waitForReEnterEmailTextboxInvisibleNotInDOM();

        Assert.assertTrue(loginPage.isReEnterEmailTextboxUndisplayed());
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
