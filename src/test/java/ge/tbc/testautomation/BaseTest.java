package ge.tbc.testautomation;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    public SoftAssert sfa = new SoftAssert();
    public RemoteWebDriver driver;
    public static String userName, accessKey;
    public static Map<String, Object> browserStackYamlMap;
    public static final String USER_DIR = "user.dir";

    public BaseTest() {
        File file = new File(getUserDir() + "/browserstack.yml");
        browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
        userName = System.getenv("BROWSERSTACK_USERNAME") != null ?
                System.getenv("BROWSERSTACK_USERNAME") :
                (String) browserStackYamlMap.get("userName");
        accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY") != null ?
                System.getenv("BROWSERSTACK_ACCESS_KEY") :
                (String) browserStackYamlMap.get("accessKey");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        MutableCapabilities capabilities = new MutableCapabilities();

        // Setup BrowserStack options
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("source", "selenide:sample-master:v1.2");
        bstackOptions.put("resolution", "1920x1080");
        capabilities.setCapability("bstack:options", bstackOptions);

        // Initialize RemoteWebDriver with BrowserStack URL
        String remoteUrl = String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub", userName, accessKey);
        driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);

        // Configure timeouts and maximize window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        // Set Selenide's WebDriver
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (WebDriverRunner.getWebDriver() != null) {
            WebDriverRunner.getWebDriver().quit();
        }
    }

    private String getUserDir() {
        return System.getProperty(USER_DIR);
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try (InputStream inputStream = Files.newInputStream(yamlFile.toPath())) {
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            if (config != null) {
                map.putAll(config);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        sfa.assertAll();
    }
}