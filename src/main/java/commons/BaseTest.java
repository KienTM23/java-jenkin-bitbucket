package commons;

import factoryEnvironment.EnvironmentList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class BaseTest {
    private WebDriver driver;
    protected final Logger log;
    String projectPath = GlobalConstants.PROJECT_PATH;

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList) {
            case FIREFOX:
                //Latest browser driver version
//                WebDriverManager.firefoxdriver().setup();
                //Specific browser driver version
//                WebDriverManager.firefoxdriver().driverVersion("").setup();
//                //Base on : Browser version
//                WebDriverManager.firefoxdriver().browserVersion("").setup();
//                driver = new FirefoxDriver();
                //tu khoi tao driver
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case CHROME:
//                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case EDGE:
//                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        driver.get(GlobalConstants.LIVE_USER_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String urlValue) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList) {
            case FIREFOX:
//                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case FIREFOX_HEADLESS:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                options.addArguments("window-size=1920x1080");
                driver = new FirefoxDriver(options);
//                FirefoxOptions options = new FirefoxOptions();
//                options.addArguments("-headless");
//                driver = WebDriverManager.firefoxdriver().capabilities(options).create();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.args", "--disable-logging");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                File chromeTranslateFile = new File(projectPath + "\\browserExtensions\\google_translate.crx");
                ChromeOptions chromeOptionFile = new ChromeOptions();
//                chromeOptionFile.addExtensions(chromeTranslateFile);
//                chromeOptionFile.addArguments("--lang=vi");
                chromeOptionFile.setExperimentalOption("useAutomationExtension", false);
                chromeOptionFile.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptionFile.setExperimentalOption("prefs", prefs);
                driver = WebDriverManager.chromedriver().create();
                break;
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-size=1920x1080");
                driver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
                break;
            case EDGE:
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        driver.get(urlValue);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String serverName, String roleName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList) {
            case FIREFOX:
//                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
                driver = (WebDriverManager.firefoxdriver().create());
                break;
            case FIREFOX_HEADLESS:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                options.addArguments("window-size=1920x1080");
                driver = (new FirefoxDriver(options));
//                FirefoxOptions options = new FirefoxOptions();
//                options.addArguments("-headless");
//                driver = WebDriverManager.firefoxdriver().capabilities(options).create();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.args", "--disable-logging");
                System.setProperty("webdriver.chrome.silentOutput", "true");
                File chromeTranslateFile = new File(projectPath + "\\browserExtensions\\google_translate.crx");
                ChromeOptions chromeOptionFile = new ChromeOptions();
//                chromeOptionFile.addExtensions(chromeTranslateFile);
//                chromeOptionFile.addArguments("--lang=vi");
                chromeOptionFile.setExperimentalOption("useAutomationExtension", false);
                chromeOptionFile.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                chromeOptionFile.setExperimentalOption("prefs", prefs);
                driver = (WebDriverManager.chromedriver().create());
                break;
            case CHROME_HEADLESS:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-size=1920x1080");
                driver = (WebDriverManager.chromedriver().capabilities(chromeOptions).create());
                break;
            case EDGE:
                driver = (WebDriverManager.edgedriver().create());
                break;
            default:
                throw new RuntimeException("Browser name is not valid!");
        }

        driver.get(getAppUrlByRoleName(serverName, roleName));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    private String getAppUrlByRoleName(String serverName, String roleName) {
        if (roleName.toLowerCase().equals("user")) {
            return getUserAppUrlByServerName(serverName);
        } else {
            return getAdminAppUrlByServerName(serverName);
        }
    }

    private String getUserAppUrlByServerName(String serverName) {
        ServerList serverList = ServerList.valueOf(serverName.toUpperCase());
        switch (serverList) {
            case DEV:
                return GlobalConstants.DEV_USER_URL;
            case TESTING:
                return GlobalConstants.TEST_USER_URL;
            case STAGING:
                return GlobalConstants.STAGING_USER_URL;
            case LIVE:
                return GlobalConstants.LIVE_USER_URL;
            default:
                throw new RuntimeException("Server name is not valid!");
        }
    }

    private String getAdminAppUrlByServerName(String serverName) {
        ServerList serverList = ServerList.valueOf(serverName.toUpperCase());
        switch (serverList) {
            case DEV:
                return GlobalConstants.DEV_ADMIN_URL;
            case TESTING:
                return GlobalConstants.TEST_ADMIN_URL;
            case STAGING:
                return GlobalConstants.STAGING_ADMIN_URL;
            case LIVE:
                return GlobalConstants.LIVE_ADMIN_URL;
            default:
                throw new RuntimeException("Server name is not valid!");
        }
    }


    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
            log.info("----------Passed----------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailuresForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("----------Failed----------");
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
            log.info("----------Passed----------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailuresForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("----------Failed----------");
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info("----------Passed----------");
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailuresForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            log.info("----------Failed----------");
        }
        return status;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        deleteFilesInReportNGFolder();
    }

    private void deleteFilesInReportNGFolder() {
        try {
            File file = new File(GlobalConstants.REPORT_SCREENSHOT_PATH);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME;
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("Windows")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void showBrowserConsoleLogs() {
        if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
            LogEntries logs = driver.manage().logs().get("browser");
            List<LogEntry> logList = logs.getAll();
            for (LogEntry logging : logList) {
                if (logging.getLevel().toString().toLowerCase().contains("error")) {
                    log.info("----------" + logging.getLevel().toString() + "------------\n" + logging.getMessage());
                }
            }
        }
    }

    protected String getEnviromentUrl(String environmentName) {
        String envUrl = null;
        EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
        switch (environment) {
            case DEV:
                envUrl = GlobalConstants.DEV_USER_URL;
                break;
            case TESTING:
                envUrl = GlobalConstants.DEV_ADMIN_URL;
                break;
            default:
                envUrl = null;
                break;
        }
        return envUrl;
    }

    protected String getCurrentTime() {
        Date date = new Date();
        return date.toString();
    }

    protected int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(999999);
    }
}
