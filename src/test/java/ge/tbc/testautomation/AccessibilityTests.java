package ge.tbc.testautomation;

import ge.tbc.testautomation.steps.SwoopSteps;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Swoop Bonus2")
@Feature("Accessibility And Visual Testing")
public class AccessibilityTests extends BaseTest {
    SwoopSteps swoopSteps = new SwoopSteps();

    @Story("Verify accessibility compliance on the homepage")
    @Description("Checks the homepage for accessibility issues using aXe.")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1)
    public void homepageAccessibilityTest() {
        swoopSteps.openSwoopPage()
                .validatePageAccessibility()
                .checkSoftAssert();
    }

    @Story("Verify visual change after language switch")
    @Description("Captures screenshots before and after language switch, then compares them pixel by pixel.")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void dynamicLoadingVisualTest() throws IOException {
        swoopSteps.openSwoopPage()
                .validateVisualChange()
                .checkSoftAssert();
    }
}