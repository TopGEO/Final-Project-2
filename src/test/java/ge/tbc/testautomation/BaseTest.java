package ge.tbc.testautomation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    public SoftAssert sfa = new SoftAssert();

    // Using a single WebDriver instance across multiple tests can lead to state leakage
    // So we will use BeforeMethod and AfterMethod (also, its required in homework)
    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1366,728");
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
    }

    @AfterMethod
    public void tearDown() {
        getWebDriver().quit();
    }

    @AfterClass()
    public void tearDownClass() {
        sfa.assertAll();
    }
}