package user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyDashboardPageObject  extends BasePageFactory {

    private WebDriver driver;
    public MyDashboardPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']")
    WebElement contactInforText;

    public boolean isContactInforDisplayed(String contactInfor) {
        waitForElementVisible(driver, contactInfor);
        String actualContactInforText = getElementText(driver,contactInfor);
        return actualContactInforText.contains(contactInfor);
    }
}
