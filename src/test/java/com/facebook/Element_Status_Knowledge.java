package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Element_Status_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait;
    By reEnterEmail = By.cssSelector("input[name='reg_email_confirmation']");

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
        driver.get("https://www.facebook.com");
    }

    @Test
    public void TC_01_Visible(){
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

        driver.findElement(By.name("reg_email__")).sendKeys("automation@gmail.com");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reEnterEmail));

        Assert.assertTrue(driver.findElement(reEnterEmail).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_In_DOM(){
        driver.findElement(By.name("reg_email__")).clear();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reEnterEmail));

        Assert.assertFalse(driver.findElement(reEnterEmail).isDisplayed());
    }
    @Test
    public void TC_03_Invisible_Not_In_DOM(){
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reEnterEmail));

        Assert.assertFalse(driver.findElement(reEnterEmail).isDisplayed());

    }
    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}
