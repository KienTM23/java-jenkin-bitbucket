package com.jquery.dataTable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.JQuery.HomePageObject;
import pageObjects.navigation.PageGeneratorManager;

public class Level_12_DataTable_Part_II extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    @Parameters({"browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName,String url) {
       driver = getBrowserDriver(browserName,url);
       homePage = PageGeneratorManager.getHomePage(driver);
       driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Action() {
        // Lay ra duoc thu tu cua cot khi biet truoc ten cot

        // Nhap lieu
        homePage.enterToTextboxByHeaderNameAndRowNumber("Contact Person","2","Automation Test");
        homePage.sleepInSecond(2);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
