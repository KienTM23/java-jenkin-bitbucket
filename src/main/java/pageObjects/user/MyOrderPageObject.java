package pageObjects.user;

import org.openqa.selenium.WebDriver;
import pageObjects.navigation.SideBarMyAccountPageObject;

public class MyOrderPageObject  extends SideBarMyAccountPageObject {
    private WebDriver driver;
    public MyOrderPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }


}
