package com.techpanda.account;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_Apply_BasePage_Part_III extends BasePage{
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @BeforeMethod
    public void beforeMethod() {
        openPageUrl(driver,"http://live.techpanda.org/");

        clickToElement(driver,"//div[@class='footer']//a[text()='My Account']");
    }

    @Test
    public void TC_01_LoginWithEmptyEmailAndPassword() {
        sendkeyToElement(driver,"//input[@id='email']","");
        sendkeyToElement(driver,"//input[@id='pass']","");

        clickToElement(driver,"//button[@id='send2']");

        Assert.assertEquals(getElementText(driver,"//div[@id='advice-required-entry-email']"),"This is a required field.");
        Assert.assertEquals(getElementText(driver,"//div[@id='advice-required-entry-pas']"),"This is a required field.");
    }

    @Test
    public void TC_02_LoginWithInvalidEmail() {
        sendkeyToElement(driver,"//input[@id='email']","123@456.789");
        sendkeyToElement(driver,"//input[@id='pass']","123456");
        clickToElement(driver,"//button[@id='send2']");

        Assert.assertEquals(getElementText(driver,"//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test(description = "Email not exist in application")
    public void TC_03_LoginWithIncorrectEmail() {
        sendkeyToElement(driver,"//input[@id='email']","auto_test" + randomNumber() + "@live.com");
        sendkeyToElement(driver,"//input[@id='pass']","123456");
        clickToElement(driver,"//button[@id='send2']");

        Assert.assertEquals(getElementText(driver,"//li[@class='error-msg']//span"), "Invalid login or password.");
    }

    @Test(description = "Password less than 6 characters")
    public void TC_04_LoginWithInvalidPassword() {
        sendkeyToElement(driver,"//input[@id='email']","auto_test" + randomNumber() + "@live.com");
        sendkeyToElement(driver,"//input[@id='pass']","123");
        clickToElement(driver,"//button[@id='send2']");

        Assert.assertEquals(getElementText(driver,"//div[@id='advice-validate-email-email']"), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_05_LoginWithIncorrectPassword() {
        sendkeyToElement(driver,"//input[@id='email']","auto_test" + randomNumber() + "@live.com");
        sendkeyToElement(driver,"//input[@id='pass']",randomNumber() + "");
        clickToElement(driver,"//button[@id='send2']");

        Assert.assertEquals(getElementText(driver,"//li[@class='error-msg']//span"), "Invalid login or password.");
    }

    @Test
    public void TC_06_LoginWithValidEmailAndPassword() {
        sendkeyToElement(driver,"//input[@id='email']","automationfc.vn@gmail.com");
        sendkeyToElement(driver,"//input[@id='pass']","123123");
        clickToElement(driver,"//button[@id='send2']");

        Assert.assertTrue(isElementDisplayedInDOM(driver,"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(.,'Automation FC')]"));
        Assert.assertTrue(isElementDisplayedInDOM(driver,"//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(.,'automationfc.vn@gmail.com')]"));

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
