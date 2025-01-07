package ge.tbc.testautomation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    public SoftAssert sfa = new SoftAssert();

    // we will use BeforeMethod and AfterMethod (its required in homework)

    @BeforeMethod(alwaysRun = true) // this tells TestNG that "even if im filtering test methods by group, run this @BeforeMethod anyway"
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1366,728");
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
    }

//    @BeforeMethod
//    public void setUp() throws MalformedURLException {
//        // --- BROWSERSTACK CREDENTIALS ---
//        String userName = System.getenv("topgeo_o2IE4g");  // or put them in a config file
//        String accessKey = System.getenv("Lqhztdz9Fyb9rsq39G7p");
//
//        // Create your desired capabilities for cross-browser
//        MutableCapabilities browserOptions = new MutableCapabilities();
//        browserOptions.setCapability("browserName", "Chrome");
//        browserOptions.setCapability("browserVersion", "latest");
//
//        // Build the 'bstack:options' for advanced config
//        Map<String, Object> bstackOptions = new HashMap<>();
//        bstackOptions.put("os", "Windows");
//        bstackOptions.put("osVersion", "11");
//        bstackOptions.put("sessionName", "My Project Test Session"); // name your test session
//        bstackOptions.put("local", "false");                        // or true if you are testing local URLs
//        // You can add more advanced capabilities here (resolution, debug, network logs, etc.)
//
//        browserOptions.setCapability("bstack:options", bstackOptions);
//
//        // --- SELENIDE CONFIG ---
//        Configuration.browserCapabilities = browserOptions;
//        Configuration.remote = "https://" + userName + ":" + accessKey + "@hub.browserstack.com/wd/hub";
//        Configuration.browser = "chrome";  // not used in local sense, but sets the 'browser' variable
//        Configuration.timeout = 10000;
//        Configuration.browserSize = "1920x1080";
//
//        // we can also do it manually, if we wish to.
//        // WebDriver driver = new RemoteWebDriver(
//        //      new URL("https://" + userName + ":" + accessKey + "@hub.browserstack.com/wd/hub"),
//        //      browserOptions
//        // );
//        // WebDriverRunner.setWebDriver(driver);
//    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        getWebDriver().quit();
    }

    @AfterClass(alwaysRun = true) // after class is needed, since testng.xml's filtering ignores if its AfterSuite
    public void tearDownClass() {
        sfa.assertAll();
    }
}