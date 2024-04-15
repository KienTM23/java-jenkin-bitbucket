package com.jquery.dataTable;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.JQuery.HomePageObject;
import pageObjects.navigation.PageGeneratorManager;

public class Level_12_DataTable_Part_I extends BaseTest {
    WebDriver driver;
    HomePageObject homePage;
    @Parameters({"browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName,String url) {
       driver = getBrowserDriver(browserName,url);
       homePage = PageGeneratorManager.getHomePage(driver);
       driver.manage().window().maximize();
    }

//    @Test
    public void TC_01_Header_Textbox() {
        //1 - Làm sao để nhập dữ liệu vào các textbox trong header
        //Parameter : Header Name
        //Parameter: value
        homePage.enterToTextboxByHeaderName("Country","Angola");
        homePage.sleepInSecond(2);
        //2 - Làm sao để verify dữ liệu của 1 nước nào đó

        Assert.assertTrue(homePage.isRowValuesDisplayed("276880","Angola","276472","553353"));
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Total","553353");
        homePage.sleepInSecond(2);
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Females","24128");
        homePage.sleepInSecond(2);
        homePage.refreshCurrentPage(driver);
//        Assert.assertTrue(homePage.isRowValuesDisplayed("276880","Angola","276472","553353"));
    }
//    @Test
    public void TC_02_Action_Icon() {
        //3 - Làm sao để click vào icon edit / delete của 1 nước bất kì
        homePage.clickToActionIconByCountryName("Angola","remove");
        homePage.sleepInSecond(2);

        homePage.refreshCurrentPage(driver);

        homePage.clickToActionIconByCountryName("Argentina","edit");
        homePage.sleepInSecond(2);
    }
    @Test
    public void TC_03_Paging() {
        //4 - Làm sao để mở đến 1 trang bất kỳ (page number)
        homePage.clickToPageByNumber("5");
        Assert.assertTrue(homePage.isPageNumberActive("5"));

        homePage.clickToPageByNumber("10");
        Assert.assertTrue(homePage.isPageNumberActive("10"));

        homePage.clickToPageByNumber("3");
        Assert.assertTrue(homePage.isPageNumberActive("3"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
