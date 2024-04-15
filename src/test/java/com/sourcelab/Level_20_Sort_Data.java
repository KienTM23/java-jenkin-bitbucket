package com.sourcelab;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.soucelab.InventoryPageObject;
import pageObjects.soucelab.LoginPageObject;
import pageObjects.soucelab.PageGeneratorManager;


public class Level_20_Sort_Data extends BaseTest {

    private LoginPageObject loginPage;
    private InventoryPageObject inventoryPage;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName,String appUrl) {
        driver = getBrowserDriver(browserName,appUrl);

        loginPage = PageGeneratorManager.getLoginPage(driver);

        loginPage.enterToUsernameTextbox("standard_user");
        loginPage.enterToPasswordTextbox("secret_sauce");
        inventoryPage = loginPage.clickToLoginButton();
    }

    @Test
    public void Sort_01_Ascending_Name() {
        inventoryPage.selectSortDropdown("Name (A to Z)");

        Assert.assertTrue(inventoryPage.isProductNameSortAscending());

    }

    @Test()
    public void Sort_02_Descending_Name() {
        inventoryPage.selectSortDropdown("Name (Z to A)");

        Assert.assertTrue(inventoryPage.isProductNameSortDescending());
    }
    @Test()
    public void Sort_03_Price() {
        inventoryPage.selectSortDropdown("Price (low to high)");
        Assert.assertTrue(inventoryPage.isProductPriceSortAscending());

        inventoryPage.selectSortDropdown("Price (high to low)");
        Assert.assertTrue(inventoryPage.isProductPriceSortDescending());
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    WebDriver driver;

}
