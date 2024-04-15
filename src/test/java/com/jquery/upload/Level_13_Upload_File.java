package com.jquery.upload;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.JQuery.HomePageObject;
import pageObjects.navigation.PageGeneratorManager;

public class Level_13_Upload_File extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    String autotestImage = "autotest.png";
    String inforMessageImage = "infor.png";
    String shoesImage = "shoes.png";
    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName,String url) {
       driver = getBrowserDriver(browserName,url);
       homePage = PageGeneratorManager.getHomePage(driver);
       driver.manage().window().maximize();
    }

    @Test
    public void TC_01_One_File() {
        homePage.uploadMultipleFiles(driver,autotestImage);

        Assert.assertTrue(homePage.isFileNameLoadedSuccess(autotestImage));

        homePage.clickToStartButton();

        Assert.assertTrue(homePage.isFileUploadedSuccess(autotestImage));
    }
    @Test
    public void TC_02_Multiple_File() {
        homePage.refreshCurrentPage(driver);
        homePage.uploadMultipleFiles(driver,autotestImage,shoesImage,inforMessageImage);

        Assert.assertTrue(homePage.isFileNameLoadedSuccess(autotestImage));
        Assert.assertTrue(homePage.isFileNameLoadedSuccess(shoesImage));
        Assert.assertTrue(homePage.isFileNameLoadedSuccess(inforMessageImage));

        homePage.clickToStartButton();

        Assert.assertTrue(homePage.isFileUploadedSuccess(autotestImage));
        Assert.assertTrue(homePage.isFileUploadedSuccess(shoesImage));
        Assert.assertTrue(homePage.isFileUploadedSuccess(inforMessageImage));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
