package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.navigation.FooterContainerPageObject;
import pageObjects.navigation.PageGeneratorManager;
import pageObjects.user.*;
import pageUIs.JQuery.HomePageUI;
import pageUIs.admin.AdminBasePageUI;
import pageUIs.user.UserBasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {

    //chinh ban than ham nay co the kho tao 1 doi tuong cua class BasePage
    //static vao ham: thuoc pham vi cua class ko thuoc pham vi cua object
    public static BasePage getBasePageInstance() {
        return new BasePage();
    }

    public Set<Cookie> getCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
        refreshCurrentPage(driver);
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    public void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String valueToSendkey) {
        waitForAlertPresence(driver).sendKeys(valueToSendkey);
    }

    public String getAlertText(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    public void switchToWindowByID(WebDriver driver, String expectedID) {
        Set<String> allTabIDs = driver.getWindowHandles();

        for (String id : allTabIDs) {
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allTabIDs = driver.getWindowHandles();

        for (String id : allTabIDs) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public String castRestParameter(String locator, String... values) {
        locator = String.format(locator, (Object[]) values);
        return locator;
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    public List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    //Locator này nhận tham số đầu vào là id/class/name/css/xpath
    //quy ước convention của by locator là : id=/ class=/ name=/ xpath=/ css=
    //id=/ Id=/ ID=/ iD=
    //Hàm này chỉ sử dụng cho class này dùng
    private By getByLocator(String locator) {
        By by = null;
        if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLASS=")) {
            by = By.className(locator.substring(6));
        } else if (locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("css=") || locator.startsWith("Css=") || locator.startsWith("CSS=")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("xpath=") || locator.startsWith("XPath=") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
            by = By.xpath(locator.substring(6));
        } else {
            throw new RuntimeException("Locator is not valid!");
        }
        return by;
    }

    public int getListElementSize(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public int getListElementSize(WebDriver driver, String locator, String... dynamicLocator) {
        return getListElement(driver, castRestParameter(locator, dynamicLocator)).size();
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... values) {
        getWebElement(driver, castRestParameter(locator, values)).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
        WebElement element = getWebElement(driver, locator);
        element.clear();
        element.sendKeys(valueToInput);
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToInput, String... dynamicLocator) {
        WebElement element = getWebElement(driver, castRestParameter(locator, dynamicLocator));
        element.clear();
        element.sendKeys(valueToInput);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
        Select select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... values) {
        Select select = new Select(getWebElement(driver, castRestParameter(locator, values)));
        select.selectByVisibleText(itemText);
    }

    public String getFirstSelectedTextItem(WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver, locator));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
        getWebElement(driver, parentXpath).click();
        sleepInSecond(2);

        List<WebElement> childItem = new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));
        for (WebElement tempElement : childItem) {
            if (tempElement.getText().trim().equals(expectedItemText)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", tempElement);
                sleepInSecond(1);
                tempElement.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        return getWebElement(driver, castRestParameter(locator, values)).getText();
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
        return getWebElement(driver, castRestParameter(locator, values)).getAttribute(attributeName);
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public int getElementSize(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isElementDisplayedInDOM(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayedInDOM(WebDriver driver, String locator, String... dynamicLocator) {
        return getWebElement(driver, castRestParameter(locator, dynamicLocator)).isDisplayed();
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator) {
        setImplicitTime(driver, shortTimeout);
        List<WebElement> elements = getListElement(driver, locator);
        setImplicitTime(driver, longTimeout);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void setImplicitTime(WebDriver driver, long timeoutInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSecond));
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverMouseToElement(WebDriver driver, String locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        Actions actions = new Actions(driver);
        actions.contextClick(getWebElement(driver, locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator) {
        Actions actions = new Actions(driver);
        actions.doubleClick(getWebElement(driver, locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String stargetLocator) {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, stargetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(driver, locator), key).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... dynamicLocator) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(driver, castRestParameter(locator, dynamicLocator)), key).perform();
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForAllElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... values) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
    }

    public void waitForElementInVisibleInDOM(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInVisibleNotInDOM(WebDriver driver, String locator) {
        setImplicitTime(driver, shortTimeout);
        new WebDriverWait(driver, Duration.ofSeconds(shortTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        setImplicitTime(driver, longTimeout);
    }

    public void waitForElementInVisibleInDOM(WebDriver driver, String locator, String... values) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... values) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, values))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... filesNames) {
        String uploadFilePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : filesNames) {
            fullFileName = fullFileName + uploadFilePath + file + "\n";
        }
        fullFileName = fullFileName.trim();
        getWebElement(driver, HomePageUI.UPLOAD_FILE).sendKeys(fullFileName);
    }


    public FooterContainerPageObject getFooterContainerPage(WebDriver driver) {
        return new FooterContainerPageObject(driver);
    }

    public AdminLoginPageObject clickToAdminLogoutLink(WebDriver driver) {
        waitForElementClickable(driver, AdminBasePageUI.LOG_OUT_LINK);
        clickToElement(driver, AdminBasePageUI.LOG_OUT_LINK);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }

    public UserHomePageObject clickToUserLogoutLink(WebDriver driver) {
        waitForElementClickable(driver, UserBasePageUI.HEADER_ACCOUNT_TEXT);
        clickToElement(driver, UserBasePageUI.HEADER_ACCOUNT_TEXT);

        waitForElementClickable(driver, UserBasePageUI.HEADER_LOG_OUT_LINK);
        clickToElement(driver, UserBasePageUI.HEADER_LOG_OUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    public AdminLoginPageObject openAdminLoginPage(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);
        return PageGeneratorManager.getAdminLoginPage(driver);
    }

    public UserHomePageObject openUserHomePage(WebDriver driver, String userUrl) {
        openPageUrl(driver, userUrl);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    //    Common function for web Component
    public void enterToTextboxByID(WebDriver driver, String textboxID, String valueToInput) {
        waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_TEXT_BY_ID, textboxID);
        sendkeyToElement(driver, AdminBasePageUI.DYNAMIC_TEXT_BY_ID, valueToInput, textboxID);
    }

    public void clickToButtonByTitle(WebDriver driver, String titleValue) {
        waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_BUTTON_BY_TITLE, titleValue);
        clickToElement(driver, AdminBasePageUI.DYNAMIC_BUTTON_BY_TITLE, titleValue);
    }

    public String getFieldErrorMessageByID(WebDriver driver, String fieldID) {
        waitForElementVisible(driver, AdminBasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
        return getElementText(driver, AdminBasePageUI.DYNAMIC_FIELD_ERROR_MESSAGE_BY_ID, fieldID);
    }

    public String getPageErrorMessage(WebDriver driver) {
        waitForElementVisible(driver, AdminBasePageUI.PAGE_ERROR_MESSAGE);
        return getElementText(driver, AdminBasePageUI.PAGE_ERROR_MESSAGE);
    }

    public String getPageSuccessMessage(WebDriver driver) {
        waitForElementVisible(driver, AdminBasePageUI.PAGE_SUCCESS_MESSAGE);
        return getElementText(driver, AdminBasePageUI.PAGE_SUCCESS_MESSAGE);
    }

    public void selectDropdownByID(WebDriver driver, String dropdownID, String dropdownItem) {
        waitForElementClickable(driver, AdminBasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
        selectItemInDefaultDropdown(driver, AdminBasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownItem, dropdownID);
    }

    private long longTimeout = GlobalConstants.LONG_TIMEOUT;
    private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
}
