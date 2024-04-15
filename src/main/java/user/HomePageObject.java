package user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends BasePageFactory {
    private WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //UI
    @CacheLookup
    @FindBy(xpath = "//div[@class='footer']//a[@title='My Account']")
    WebElement myAccountLink;
    //Action
    public void clickToMyAccountLink(){
        clickToElement(driver,myAccountLink);
        clickToElement(driver,myAccountLink);
    }

}
