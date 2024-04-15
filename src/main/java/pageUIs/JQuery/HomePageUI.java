package pageUIs.JQuery;

public class HomePageUI {
    public  static final String HEADER_TEXTBOX_BY_HEADER_NAME = "xpath=//div[text()='%s']/parent::div/following-sibling::input";
    public  static final String ROW_VALUES = "xpath=//td[@data-key='females' and text()='%s']/following-sibling::td[@data-key='country' and text()='%s']/following-sibling::td[@data-key='males' and text()='%s']/following-sibling::td[@data-key='total' and text()='%s']";
    public  static final String ACTION_ICON_BY_COUNTRY_NAME = "xpath=//td[@data-key='country' and text()='%s']/preceding-sibling::td/button[contains(@class,'%s')]";
    public  static final String PAGING_BY_PAGE_NUMBER = "xpath=//a[text()='%s']";
    public  static final String PAGING_ACTIVE_BY_PAGE_NUMBER = "xpath=//a[contains(@class,'active') and text()='%s']";


    public static final String HEADER_INDEX_BY_NAME = "xpath=//th[text()='%s']/preceding-sibling::th";
    public static final String CELL_BY_COLUMN_INDEX_AND_ROW_INDEX = "xpath=//tr[%s]/td[%s]/input";
    public static final String UPLOAD_FILE = "css=input[type='file']";
    public static final String IMAGE_FILE_NAME_LOADED = "xpath=//p[@class='name' and text()='%s']";
    public static final String IMAGE_FILE_NAME_UPLOADED = "xpath=//p[@class='name']/a[@title='%s']";
    public static final String START_BUTTON = "css=table button.start";

}
