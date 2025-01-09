package ge.tbc.testautomation;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class BaseTest {
    // we will use BeforeMethod and AfterMethod (its required in homework)
    // 'alwaysRun = true' tells TestNG that "even if im filtering test methods by group, run this @BeforeMethod anyway"
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless", "--disable-gpu", "--window-size=1366,728");
        WebDriverRunner.setWebDriver(new ChromeDriver(options));
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        WebDriverRunner.getWebDriver().manage().window().maximize();
        Configuration.timeout = 10000;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }
}