package pageObjects.JQuery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.JQuery.HomePageUI;

import java.util.List;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToTextboxByHeaderName(String headerName, String value) {
        waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_HEADER_NAME,headerName);
        sendkeyToElement(driver,HomePageUI.HEADER_TEXTBOX_BY_HEADER_NAME,value,headerName);
        pressKeyToElement(driver,HomePageUI.HEADER_TEXTBOX_BY_HEADER_NAME, Keys.ENTER,headerName);
    }

    public boolean isRowValuesDisplayed(String femaleValue , String countryName, String maleValue, String totalValue) {
        waitForElementVisible(driver,HomePageUI.ROW_VALUES,femaleValue,countryName,maleValue,totalValue);
        return isElementDisplayedInDOM(driver,HomePageUI.ROW_VALUES,femaleValue,countryName,maleValue,totalValue);
    }

    public void clickToActionIconByCountryName(String countryName, String actionName) {
        waitForElementClickable(driver,HomePageUI.ACTION_ICON_BY_COUNTRY_NAME,countryName,actionName);
        clickToElement(driver,HomePageUI.ACTION_ICON_BY_COUNTRY_NAME,countryName,actionName);
    }

    public boolean isPageNumberActive(String pageNumber) {
        waitForElementVisible(driver,HomePageUI.PAGING_ACTIVE_BY_PAGE_NUMBER,pageNumber);
        return isElementDisplayedInDOM(driver,HomePageUI.PAGING_ACTIVE_BY_PAGE_NUMBER,pageNumber);
    }

    public void clickToPageByNumber(String pageNumber) {
        waitForElementClickable(driver,HomePageUI.PAGING_BY_PAGE_NUMBER,pageNumber);
        clickToElement(driver,HomePageUI.PAGING_BY_PAGE_NUMBER,pageNumber);
    }

    public void enterToTextboxByHeaderNameAndRowNumber(String headerName, String rowNumber, String valueToInput) {
        int headerIndex = getListElementSize(driver,HomePageUI.HEADER_INDEX_BY_NAME,headerName) + 1;
        waitForElementVisible(driver,HomePageUI.CELL_BY_COLUMN_INDEX_AND_ROW_INDEX,rowNumber, String.valueOf(headerIndex));
        sendkeyToElement(driver,HomePageUI.CELL_BY_COLUMN_INDEX_AND_ROW_INDEX,valueToInput,rowNumber, String.valueOf(headerIndex));
    }

    public boolean isFileNameLoadedSuccess(String fileName) {
        waitForElementVisible(driver,HomePageUI.IMAGE_FILE_NAME_LOADED,fileName);
        return isElementDisplayedInDOM(driver,HomePageUI.IMAGE_FILE_NAME_LOADED,fileName);
    }

    public void clickToStartButton() {
        List<WebElement> startButtonElements = getListElement(driver,HomePageUI.START_BUTTON);
        for (WebElement startButton : startButtonElements){
            waitForElementClickable(driver,startButton);
            startButton.click();
            sleepInSecond(2);
        }
    }

    public boolean isFileUploadedSuccess(String fileName) {
        waitForElementVisible(driver,HomePageUI.IMAGE_FILE_NAME_UPLOADED,fileName);
        return isElementDisplayedInDOM(driver,HomePageUI.IMAGE_FILE_NAME_UPLOADED,fileName);
    }
}
