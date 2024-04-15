package commons;

import java.io.File;

public class GlobalConstants {
    //System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String FILE_SEPARATOR = File.separator;

    //App Infor User
    public static final String DEV_USER_URL = "http://dev.techpanda.org/";
    public static final String STAGING_USER_URL = "http://staging.techpanda.org/";
    public static final String LIVE_USER_URL = "http://live.techpanda.org/";
    public static final String TEST_USER_URL = "http://live.techpanda.org/";
    //App Infor Admin
    public static final String DEV_ADMIN_URL = "http://dev.techpanda.org/index.php/backendlogin/";
    public static final String STAGING_ADMIN_URL = "http://staging.techpanda.org/index.php/backendlogin/";
    public static final String LIVE_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin/";
    public static final String TEST_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin/";

    public static final String ADMIN_USERNAME = "user01";
    public static final String ADMIN_PASSWORD = "guru99com";
    //wait Infor
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 15;
    //Download/ Upload File
    public static final String UPLOAD_PATH = PROJECT_PATH + "/uploadFiles/";
    public static final String DOWNLOAD_PATH = PROJECT_PATH + "/downloadFiles/";

    public static final String DATA_PATH = PROJECT_PATH + FILE_SEPARATOR + "dataTest" +FILE_SEPARATOR;

    //Retry Case Failed
    public static final int RETRY_NUMBER = 3;
    //Browser Logs/Extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + "/browserLogs/";
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + "/browserExtensions/";
    //HTML Report Folder
    public static final String REPORT_SCREENSHOT_PATH = PROJECT_PATH + "/screenshotReportNG/";
    public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent/";
    public static final String ALLURE_PATH = getFolderSeparator("htmlAllure");
    private static String getFolderSeparator(String folderName){
        return PROJECT_PATH + FILE_SEPARATOR + folderName + FILE_SEPARATOR;
    }
}
