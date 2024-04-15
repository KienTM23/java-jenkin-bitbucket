package pageObjects.soucelab;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.soucelab.InventoryPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPageObject extends BasePage {
    WebDriver driver;

    public InventoryPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortDropdown(String valueItem) {
        waitForElementClickable(driver, InventoryPageUI.SORT_DROPDOWN);
        selectItemInDefaultDropdown(driver,InventoryPageUI.SORT_DROPDOWN,valueItem);
    }

    public boolean isProductNameSortDescending() {
        waitForAllElementVisible(driver,InventoryPageUI.PRODUCT_NAME);
        List<WebElement>productNames = getListElement(driver,InventoryPageUI.PRODUCT_NAME);
        //UI
        List<String> productNameTextUI = new ArrayList<String>();

        for (WebElement product : productNames){
            productNameTextUI.add(product.getText());
        }
        //Sort
        List<String> productNameTextSort = new ArrayList<String>(productNameTextUI);
        //Collection sort
        Collections.sort(productNameTextSort);
        //Reverse Data
        Collections.reverse(productNameTextSort);

        return productNameTextSort.equals(productNameTextUI);
    }

    public boolean isProductNameSortAscending() {
        waitForAllElementVisible(driver,InventoryPageUI.PRODUCT_NAME);
        List<WebElement>productNames = getListElement(driver,InventoryPageUI.PRODUCT_NAME);
        //UI
        List<String> productNameTextUI = new ArrayList<String>();

        for (WebElement product : productNames){
            productNameTextUI.add(product.getText());
        }
        //Sort
        List<String> productNameTextSort = new ArrayList<String>(productNameTextUI);
        //Collection sort
        Collections.sort(productNameTextSort);

        return productNameTextSort.equals(productNameTextUI);
    }
    public boolean isProductNameSortAscendingJava8() {
        waitForAllElementVisible(driver,InventoryPageUI.PRODUCT_NAME);
        List<WebElement>productNames = getListElement(driver,InventoryPageUI.PRODUCT_NAME);
        //UI
        List<String> productNameTextUI =productNames.stream().map(n -> n.getText()).collect(Collectors.toList());
        
        //Sort
        List<String> productNameTextSort = new ArrayList<String>(productNameTextUI);
        //Collection sort
        Collections.sort(productNameTextSort);

        return productNameTextSort.equals(productNameTextUI);
    }

    public boolean isProductPriceSortAscending() {
        waitForAllElementVisible(driver,InventoryPageUI.PRODUCT_PRICE);
        List<WebElement>productPrices = getListElement(driver,InventoryPageUI.PRODUCT_PRICE);
        //UI
        List<Float> productPriceTextUI = new ArrayList<Float>();

        for (WebElement product : productPrices){
//            String priceText = product.getText();
//            priceText = priceText.replace("$","").replace(",","");
//
//            Float priceNumber = Float.valueOf(priceText);
            productPriceTextUI.add(Float.valueOf(product.getText().replace("$","").replace(",","")));
        }
        //Sort
        List<Float> productPriceTextSort = new ArrayList<Float>(productPriceTextUI);
        //Collection sort
        Collections.sort(productPriceTextSort);

        return productPriceTextSort.equals(productPriceTextUI);
    }

    public boolean isProductPriceSortDescending() {
        waitForAllElementVisible(driver,InventoryPageUI.PRODUCT_PRICE);
        List<WebElement>productPrices = getListElement(driver,InventoryPageUI.PRODUCT_PRICE);
        //UI
        List<Float> productPriceTextUI = new ArrayList<Float>();

        for (WebElement product : productPrices){
            productPriceTextUI.add(Float.valueOf(product.getText().replace("$","").replace(",","")));
        }
        //Sort
        List<Float> productPriceTextSort = new ArrayList<Float>(productPriceTextUI);
        //Collection sort
        Collections.sort(productPriceTextSort);
        //
        Collections.reverse(productPriceTextSort);
        return productPriceTextSort.equals(productPriceTextUI);
    }
}
