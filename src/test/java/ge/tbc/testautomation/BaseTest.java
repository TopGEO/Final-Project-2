package ge.tbc.testautomation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseTest {
    public SoftAssert sfa = new SoftAssert();

    // we will use BeforeMethod and AfterMethod (its required in homework)
    // this tells TestNG that "even if im filtering test methods by group, run this @BeforeMethod anyway"
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1366,728");
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
    }

    @AfterClass(alwaysRun = true) // after class is needed, since testng.xml's filtering ignores if its AfterSuite
    public void tearDownClass() {
        sfa.assertAll();
    }
}